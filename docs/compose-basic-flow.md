# Compose Basic Flow

Jetpack Compose를 사용할 때 대부분의 프로젝트에서 공통으로 지키면 좋은 기본 작성 흐름입니다.

특정 프로젝트에만 해당하는 규칙이라기보다, Compose UI를 안정적으로 작성하기 위해 기본적으로 따르는 원칙을 정리합니다.

---

## 1. State Down, Event Up

Compose 화면은 기본적으로 **상태는 아래로 전달하고, 이벤트는 위로 올리는 구조**를 따릅니다.

Composable이 직접 상태를 만들고 처리하기보다, 상위 계층에서 상태를 내려주고 Composable은 그 상태를 화면에 보여주는 역할을 합니다.

사용자의 클릭, 입력 변경, 선택 같은 이벤트는 람다를 통해 상위 계층으로 전달합니다.

### 좋은 예시

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

### 피해야 하는 예시

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

Composable 내부에서 직접 navigation을 수행하면 컴포넌트가 특정 화면 이동 방식에 묶입니다. 따라서 재사용성이 떨어지고 테스트하기도 어려워집니다.

---

## 2. Composable은 가능한 한 Stateless하게 작성

Composable은 가능하면 직접 상태를 소유하지 않고, 외부에서 받은 상태를 화면에 보여주는 역할에 집중합니다.

### 좋은 예시

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

### 피해야 하는 예시

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

이메일, 비밀번호, 서버에서 받아온 데이터처럼 화면 상태에 해당하는 값은 ViewModel에서 관리하는 것이 좋습니다.

단, 드롭다운 열림 여부, 임시 선택 상태, 애니메이션 상태처럼 화면 내부에서만 필요한 UI 상태는 `remember`를 사용할 수 있습니다.

---

## 3. Modifier는 외부에서 주입받기

재사용 가능한 Composable은 `modifier: Modifier = Modifier`를 파라미터로 받는 것이 좋습니다.

이렇게 하면 컴포넌트 내부에서 크기와 위치를 강제하지 않고, 사용하는 쪽에서 조절할 수 있습니다.

### 좋은 예시

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

### 사용하는 쪽 예시

```kotlin
SignalCard(
    title = signal.title,
    summary = signal.summary,
    modifier = Modifier.fillMaxWidth(),
    onClick = { onSignalClick(signal.id) },
)
```

재사용 컴포넌트 내부에서 `fillMaxSize()`, `width()`, `height()`, `padding()` 등을 강제하면 재사용성이 떨어질 수 있습니다.

특히 `fillMaxSize()`는 Screen 단위에서는 사용할 수 있지만, Component 내부에서는 사용을 피하는 것이 좋습니다.

---

## 4. StateFlow는 Lifecycle-aware하게 수집

Compose에서 `StateFlow`를 수집할 때는 `collectAsState()`보다 `collectAsStateWithLifecycle()` 사용을 우선합니다.

### 권장 예시

```kotlin
val uiState by viewModel.uiState.collectAsStateWithLifecycle()
```

이 방식은 화면의 lifecycle을 고려해서 상태를 수집하므로 Android Compose 화면에서 더 안전하게 사용할 수 있습니다.

---

## 5. UiState와 SideEffect 분리

화면에 계속 유지되어야 하는 상태는 `UiState`로 관리하고, 한 번만 발생해야 하는 이벤트는 `SideEffect`로 분리합니다.

### UiState에 넣기 좋은 것

- 화면에 보여줄 데이터
- 로딩 여부
- 빈 상태 여부
- 에러 상태
- 선택된 값
- 입력값

### SideEffect로 분리하는 것이 좋은 것

- Navigation
- Toast
- Snackbar
- Dialog 표시
- 일회성 메시지
- 권한 요청
- 외부 앱 실행

### 예시

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

Navigation이나 Toast 같은 일회성 이벤트를 `UiState`에 넣으면, recomposition이나 화면 재진입 시 이벤트가 다시 실행될 수 있습니다.

따라서 한 번만 발생해야 하는 동작은 SideEffect로 분리하는 것이 좋습니다.

---

## 6. Preview는 실제 의존성을 사용하지 않기

Preview에서는 ViewModel, Repository, Network를 직접 사용하지 않습니다.

Preview는 UI 확인을 위한 용도이므로 fake state나 sample model을 사용합니다.

### 좋은 예시

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

### 피해야 하는 예시

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

Preview에서는 실제 의존성을 연결하지 않고, 화면에 필요한 상태만 직접 넣어 확인합니다.

---

## 7. 리스트는 안정적인 key 사용

`LazyColumn`, `LazyRow`, grid 등을 사용할 때는 가능하면 stable key를 지정합니다.

### 좋은 예시

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

key를 지정하면 리스트 변경 시 Compose가 아이템을 더 안정적으로 추적할 수 있습니다.

---

## 8. 화면 상태 모델은 명확하게 정의

화면에서 필요한 데이터는 API 응답 모델을 그대로 사용하지 않고, 화면에 맞는 `UiState` 또는 `UiModel`로 정의합니다.

### 좋은 예시

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

### 피해야 하는 예시

```kotlin
@Composable
fun SignalScreen(
    response: SignalResponse,
) {
    Text(text = response.data.project.signal.title)
}
```

API 응답 구조가 UI에 직접 들어오면 서버 응답 변경이 UI 코드까지 영향을 줄 수 있습니다.

따라서 ViewModel 또는 mapper에서 화면에 필요한 형태로 변환한 뒤 UI에 전달하는 것이 좋습니다.

---

## 9. Compose 기본 흐름 요약

Compose 기본 흐름은 다음과 같습니다.

1. 상태는 상위에서 관리한다.
2. Composable은 상태를 받아 UI를 그린다.
3. 이벤트는 람다로 상위에 전달한다.
4. 재사용 컴포넌트는 ViewModel과 NavController를 직접 참조하지 않는다.
5. Modifier는 외부에서 주입받는다.
6. StateFlow는 lifecycle-aware하게 수집한다.
7. UiState와 SideEffect를 분리한다.
8. Preview에서는 fake state를 사용한다.
9. Lazy list에는 stable key를 사용한다.
10. API 응답 모델을 UI에 직접 노출하지 않는다.
