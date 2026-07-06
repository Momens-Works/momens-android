# Momens Project Flow

This document defines the Compose conventions followed by the Momens project.

It is more specific than the general Compose flow and exists to align with the Momens package structure, MVVM flow, and code style.

Examples use feature names that already exist in the project, such as `signal`, `project`, `brief`, `signin`, `splash`, and `main`.

---

## 1. Use Hilt ViewModels

ViewModels in the Momens project are written with Hilt.

### ViewModel Example

```kotlin
@HiltViewModel
class SignalViewModel @Inject constructor(
    private val repository: SignalRepository,
) : ViewModel()
```

Use `hiltViewModel()` in the Composable `Route` layer.

```kotlin
@Composable
fun SignalRoute(
    navigateToProject: (Long) -> Unit,
    viewModel: SignalViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignalScreen(
        uiState = uiState,
        onSignalClick = navigateToProject,
    )
}
```

Do not reference ViewModel directly from `Screen` or `Component` layers.

---

## 2. Separate Route / Screen / Component Responsibilities

Screens should be split into `Route`, `Screen`, and `Component` layers when possible.

| Layer | Responsibility |
| --- | --- |
| `XxxRoute` | Connect ViewModel, collect UiState, handle SideEffects, connect navigation callbacks |
| `XxxScreen` or `XxxContent` | Receive state and event parameters, render UI only |
| `Component` | Reusable UI unit, no direct ViewModel/NavController reference |

### Route Example

```kotlin
@Composable
fun SignalRoute(
    navigateToProject: (Long) -> Unit,
    viewModel: SignalViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignalScreen(
        uiState = uiState,
        onSignalClick = navigateToProject,
    )
}
```

### Screen Example

```kotlin
@Composable
fun SignalScreen(
    uiState: SignalUiState,
    onSignalClick: (Long) -> Unit,
) {
    SignalContent(
        signals = uiState.signals,
        onSignalClick = onSignalClick,
    )
}
```

### Component Example

```kotlin
@Composable
fun SignalCard(
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
    ) {
        Column {
            Text(text = title)
            Text(text = summary)
        }
    }
}
```

---

## 3. Common UiState Pattern

Screens that render API results should clearly separate `Loading`, `Success`, and `Failure` states.

```kotlin
sealed interface UiState<out T> {
    data object Empty : UiState<Nothing>
    data object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    data object Failure : UiState<Nothing>
}
```

In ViewModel, screen-specific state can be wrapped as `UiState<XxxUiState>`.

```kotlin
private val _uiState = MutableStateFlow<UiState<SignalUiState>>(UiState.Loading)
val uiState: StateFlow<UiState<SignalUiState>> = _uiState.asStateFlow()
```

In Route, separate UI by state.

```kotlin
when (val state = uiState) {
    UiState.Loading -> {
        LoadingScreen()
    }

    UiState.Failure -> {
        ErrorContent(
            onRetryClick = viewModel::loadSignals,
        )
    }

    is UiState.Success -> {
        SignalScreen(
            uiState = state.data,
            onSignalClick = viewModel::onSignalClick,
        )
    }

    UiState.Empty -> Unit
}
```

---

## 4. SideEffect Handling Rules

One-time events such as navigation, Toast, Snackbar, and Dialog display should not be stored in `UiState`. Separate them into `SideEffect`.

```kotlin
sealed interface SignalSideEffect {
    data class NavigateToProject(
        val projectId: Long,
    ) : SignalSideEffect

    data class ShowToast(
        val message: String,
    ) : SignalSideEffect
}
```

Expose SideEffects from the ViewModel with `MutableSharedFlow`.

```kotlin
private val _sideEffect = MutableSharedFlow<SignalSideEffect>()
val sideEffect: SharedFlow<SignalSideEffect> = _sideEffect.asSharedFlow()
```

Collect and handle SideEffects in the Route layer.

```kotlin
viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
    when (sideEffect) {
        is SignalSideEffect.NavigateToProject -> {
            navigateToProject(sideEffect.projectId)
        }

        is SignalSideEffect.ShowToast -> {
            showToast(sideEffect.message)
        }
    }
}
```

Use `collectLatestSideEffect` only when the project provides that extension. If the extension does not exist, collect SideEffects with `LaunchedEffect`.

---

## 5. Use MomensTheme

Colors, typography, and shapes should come from the project theme and design system first.

### Recommended

```kotlin
Text(
    text = title,
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.titleMedium,
)
```

After project-specific design tokens are added, prefer the agreed Momens APIs.

```kotlin
Text(
    text = title,
    color = MomensTheme.colors.gray1000,
    style = MomensTheme.typography.title1SB18,
)
```

### Avoid

```kotlin
Text(
    text = title,
    color = Color.Black,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
)
```

If a new color or typography style is needed, consider adding it to the design system instead of hardcoding it inside a screen.

---

## 6. Typed Navigation Rules

Prefer type-safe route objects or data classes over simple string routes.

```kotlin
@Serializable
data object Signal : MainTabRoute
```

Wrap NavController navigation in extension functions.

```kotlin
fun NavController.navigateToSignal(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Signal,
        navOptions = navOptions,
    )
}
```

Do not hardcode string routes inside screens.

### Avoid

```kotlin
navController.navigate("project/$id")
```

### Recommended

```kotlin
navController.navigateToProject(id)
```

---

## 7. Package Structure Rules

New files should follow the existing feature structure.

```text
presentation/{feature}/XxxRoute.kt
presentation/{feature}/XxxScreen.kt
presentation/{feature}/XxxViewModel.kt
presentation/{feature}/XxxUiState.kt
presentation/{feature}/XxxSideEffect.kt
presentation/{feature}/component
presentation/{feature}/navigation
core/designsystem/component
core/common
data/{feature}/repository
data/{feature}/repositoryimpl
data/{feature}/remote
```

Feature-specific components should be placed in the feature `component` package.

Components reused across multiple screens should be considered for `core/designsystem/component` or another shared component area.

---

## 8. MockData / Preview Rules Before API Integration

Even before APIs are connected, do not hardcode temporary data directly inside Composables.

Define the screen contract first with `XxxUiState` and required `XxxUiModel` types. Then create fake state for previews or temporary UI checks.

### Rules

- `Screen` receives `XxxUiState`, not fake data directly.
- Fake/mock data lives in a companion object of `XxxUiState` or near the preview as private values.
- Fake data should be easy to replace with ViewModel repository result mapping.
- Previews must not use ViewModel, Repository, or Network.
- Prefer `ImmutableList`, `persistentListOf()`, and `toImmutableList()` for list data.
- Keep Route / Screen / Component separation even before API integration.
- MockData is only for UI implementation and preview checks. It must not replace business logic.

### Example

```kotlin
@Immutable
data class SignalUiState(
    val userName: String = "",
    val signals: ImmutableList<SignalUiModel> = persistentListOf(),
) {
    companion object {
        val Fake = SignalUiState(
            userName = "Hyemin",
            signals = persistentListOf(
                SignalUiModel(
                    id = 1L,
                    title = "API response shape requires a decision",
                    summary = "This affects the project detail and brief screens.",
                ),
                SignalUiModel(
                    id = 2L,
                    title = "Deployment risk to review today",
                    summary = "Token refresh failure cases need to be checked.",
                ),
            ),
        )
    }
}
```

For screens where state matters, define state-specific fake values.

``` kotlin
companion object {
    val FakeNormal = SignalUiState(...)
    val FakeEmpty = SignalUiState(...)
    val FakeLoading = SignalUiState(...)
}
```

---

## 9. Strengthen Previews

Important screens and reusable components should have previews.

Preview rules:

- Write preview functions as `private`.
- Name the function so the target and state are clear.
- Wrap previews with the project theme.
- Do not use real ViewModel, Repository, or Network.
- Use fake UiState or sample models.

### Basic Preview Example

```kotlin
@Preview(showBackground = true)
@Composable
private fun SignalScreenPreview() {
    MomensTheme {
        SignalScreen(
            uiState = SignalUiState.Fake,
            onSignalClick = {},
        )
    }
}
```

### State Preview Example

```kotlin
@Preview(showBackground = true)
@Composable
private fun SignalScreenEmptyPreview() {
    MomensTheme {
        SignalScreen(
            uiState = SignalUiState.Fake.copy(
                signals = persistentListOf(),
            ),
            onSignalClick = {},
        )
    }
}
```

Preview these states when possible:

- Default state
- Empty state
- Long text state
- Many-list-items state
- Selected/unselected state
- Disabled state
- Loading state
- Error state

---

## 10. Use Immutable Collections

Use immutable collections for lists exposed through Compose UiState when possible.

```kotlin
data class SignalUiState(
    val signals: ImmutableList<SignalUiModel> = persistentListOf(),
)
```

When converting a regular `List` into screen state, use `toImmutableList()`.

```kotlin
_uiState.update {
    SignalUiState(
        signals = response.signals.toImmutableList(),
    )
}
```

If the ViewModel exposes `StateFlow<UiState<SignalUiState>>`, wrap mapped data with `UiState.Success`.

```kotlin
_uiState.update {
    UiState.Success(
        data = SignalUiState(
            signals = response.signals.toImmutableList(),
        ),
    )
}
```

This rule improves state stability in Compose and reduces confusion when lists change.

---

## 11. Prefer Design System Components

Before styling Material components directly, check existing project components for UI such as Button, TextField, TopAppBar, Chip, Loading, and BottomSheet.

When creating new components, prioritize project theme, typography, colors, and shapes.

---

## 12. Loading / Failure UI Rules

Screens that render API results should clearly handle Loading, Success, and Failure.

- Prefer common Loading components for Loading state.
- Do not leave Failure as an empty block.
- If there is no final design yet, leave a TODO comment explaining the intent or provide a minimal fallback UI.
- In Success state, use UiState already mapped by the ViewModel.

### Example

```kotlin
when (val state = uiState) {
    UiState.Loading -> {
        LoadingScreen()
    }

    UiState.Failure -> {
        ErrorContent(
            onRetryClick = viewModel::loadSignals,
        )
    }

    is UiState.Success -> {
        SignalScreen(
            uiState = state.data,
            onSignalClick = viewModel::onSignalClick,
        )
    }

    UiState.Empty -> Unit
}
```

Do not leave Failure empty like this:

``` kotlin
UiState.Failure -> Unit 
```

---

## 13. Apply `.editorconfig` / ktlint

Before and after generating code, format according to the root `.editorconfig` and ktlint configuration.

- Follow indentation, max line length, final newline, and trailing whitespace rules.
- Remove unused imports.
- Avoid wildcard imports.
- If ktlint and `.editorconfig` conflict, check the ktlint result first.

Check command:

```bash
./gradlew ktlintCheck
```

Auto-format command:

```bash
./gradlew ktlintFormat
```

---

## 14. Workflow Before API Integration

Before API integration, implement screens in this order:

1. Define the required `XxxUiState` and `XxxUiModel`.
2. Create fake or state-specific fake state for previews.
3. Make `XxxScreen` receive `XxxUiState` and event lambdas.
4. Split reusable UI into `component`.
5. Check default/empty/long-text/selected states with previews.
6. When connecting the API, map repository responses to `XxxUiState` in the ViewModel.
7. Do not leave fake/mock data inside Composables.

### Avoid

```kotlin
@Composable
fun SignalScreen() {
    val signals = listOf(
        SignalUiModel(
            id = 1L,
            title = "Temporary data",
            summary = "Temporary summary",
        ),
    )

    SignalContent(signals = signals)
}
```

### Recommended

```kotlin
@Composable
fun SignalScreen(
    uiState: SignalUiState,
    onSignalClick: (Long) -> Unit,
) {
    SignalContent(
        signals = uiState.signals,
        onSignalClick = onSignalClick,
    )
}
```

Inject fake state in previews.

```kotlin
@Preview(showBackground = true)
@Composable
private fun SignalScreenPreview() {
    MomensTheme {
        SignalScreen(
            uiState = SignalUiState.Fake,
            onSignalClick = {},
        )
    }
}
```

---

## 15. Momens Flow Summary

Momens prioritizes this flow:

1. Write ViewModels with Hilt.
2. Separate screens into Route / Screen / Component layers.
3. Let Route connect ViewModel, collect UiState, and handle SideEffects.
4. Let Screen receive UiState and event lambdas, then render UI only.
5. Write Component as a reusable UI unit.
6. Represent API state with `UiState<T>`.
7. Separate one-time events such as Navigation, Toast, and Snackbar into SideEffect.
8. Prefer `MomensTheme` for colors and typography.
9. Prefer Typed Navigation and navigation extension functions.
10. Follow the existing feature package structure for new files.
11. Even before API integration, define UiState and fake state first.
12. Do not use real dependencies in previews.
