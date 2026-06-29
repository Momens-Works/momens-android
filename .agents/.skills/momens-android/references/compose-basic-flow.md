# Compose Basic Flow

This document defines the baseline Compose flow that should be followed in most Jetpack Compose projects.

These rules are not specific to Momens. They describe general principles for writing stable, reusable, and maintainable Compose UI.

## Contents

1. State Down, Event Up
2. Keep Composables Stateless When Possible
3. Accept Modifier From The Caller
4. Collect StateFlow With Lifecycle Awareness
5. Separate UiState And SideEffect
6. Do Not Use Real Dependencies In Preview
7. Use Stable Keys For Lists
8. Define Explicit UI Models
9. Summary

---

## 1. State Down, Event Up

Compose screens should follow **state down, event up**.

Composable functions should receive state from a higher layer and render UI from that state. User actions such as clicks, input changes, and selections should be sent upward through callbacks.

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

```kotlin
@Composable
fun SignalContent(
    signals: ImmutableList<SignalUiModel>,
    onSignalClick: (Long) -> Unit,
) {
    LazyColumn {
        items(
            items = signals,
            key = { signal -> signal.id },
        ) { signal ->
            SignalCard(
                title = signal.title,
                summary = signal.summary,
                onClick = { onSignalClick(signal.id) },
            )
        }
    }
}
```

### Avoid

```kotlin
@Composable
fun SignalCard(
    signal: SignalUiModel,
    navController: NavController,
) {
    Card(
        modifier = Modifier.clickable {
            navController.navigate("project/${signal.id}")
        },
    ) {
        Text(text = signal.title)
    }
}
```

Do not perform navigation directly inside reusable components. Doing so couples the component to a specific navigation implementation and makes reuse and testing harder.

---

## 2. Keep Composables Stateless When Possible

Composable functions should usually avoid owning screen state. They should focus on rendering the state they receive.

### Recommended

```kotlin
@Composable
fun SigninTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
    )
}
```

### Avoid

```kotlin
@Composable
fun SigninTextField() {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = { value = it },
    )
}
```

Screen state such as email, password, selected values, and server data should usually be owned by the ViewModel.

Local UI-only state, such as dropdown expansion, temporary selection, focus state, or animation state, may use `remember`.

---

## 3. Accept Modifier From The Caller

Reusable Composables should expose `modifier: Modifier = Modifier`.

This lets the caller control size, position, and layout behavior instead of forcing those decisions inside the component.

### Recommended

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

### Usage

```kotlin
SignalCard(
    title = signal.title,
    summary = signal.summary,
    modifier = Modifier.fillMaxWidth(),
    onClick = { onSignalClick(signal.id) },
)
```

Avoid forcing layout modifiers such as `fillMaxSize()`, `width()`, `height()`, or `padding()` inside reusable components unless the component contract explicitly requires it.

`fillMaxSize()` is acceptable at the screen level, but it should usually be avoided inside small reusable components.

---

## 4. Collect StateFlow With Lifecycle Awareness

When collecting `StateFlow` in Compose, prefer `collectAsStateWithLifecycle()` over `collectAsState()`.

### Recommended

```kotlin
val uiState by viewModel.uiState.collectAsStateWithLifecycle()
```

This respects the Android lifecycle and is safer for Compose screens.

---

## 5. Separate UiState And SideEffect

State that must remain visible on screen should be modeled as `UiState`.

One-time events should be modeled as `SideEffect`.

### Good UiState Values

- Data shown on screen
- Loading state
- Empty state
- Error state
- Selected values
- Input values

### Good SideEffect Values

- Navigation
- Toast
- Snackbar
- Dialog display
- One-time messages
- Permission requests
- Launching external apps

### Example

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

Navigation and Toast events should not be stored in `UiState`, because recomposition or screen re-entry can accidentally trigger them again.

---

## 6. Do Not Use Real Dependencies In Preview

Previews should not use ViewModel, Repository, Network, or dependency injection.

Previews exist to inspect UI, so they should use fake state or sample models.

### Recommended

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

### Avoid

```kotlin
@Preview
@Composable
private fun SignalScreenPreview() {
    val viewModel: SignalViewModel = hiltViewModel()

    SignalRoute(
        navigateToProject = {},
        viewModel = viewModel,
    )
}
```

Pass only the state needed by the UI into previews.

---

## 7. Use Stable Keys For Lists

When using `LazyColumn`, `LazyRow`, or grids, provide stable keys whenever possible.

### Recommended

```kotlin
LazyColumn {
    items(
        items = uiState.signals,
        key = { signal -> signal.id },
    ) { signal ->
        SignalCard(
            title = signal.title,
            summary = signal.summary,
            onClick = { onSignalClick(signal.id) },
        )
    }
}
```

Stable keys help Compose track items correctly when list content changes.

---

## 8. Define Explicit UI Models

Do not expose API response models directly to Compose UI.

Define screen-specific `UiState` and `UiModel` types instead.

### Recommended

```kotlin
data class SignalUiState(
    val userName: String = "",
    val signals: ImmutableList<SignalUiModel> = persistentListOf(),
)

data class SignalUiModel(
    val id: Long,
    val title: String,
    val summary: String,
)
```

### Avoid

```kotlin
@Composable
fun SignalScreen(
    response: SignalResponse,
) {
    Text(text = response.data.project.signal.title)
}
```

If API response structures reach UI code directly, server-side changes can leak into the presentation layer. Map responses into UI models in the ViewModel or mapper layer before rendering.

---

## 9. Summary

Compose basic flow:

1. Manage state in the upper layer.
2. Pass state down to Composables.
3. Send events upward through lambdas.
4. Do not reference ViewModel or NavController from reusable components.
5. Accept `modifier` from the caller.
6. Collect `StateFlow` with lifecycle awareness.
7. Separate `UiState` and `SideEffect`.
8. Use fake state in previews.
9. Use stable keys in lazy lists.
10. Do not expose API response models directly to UI.
