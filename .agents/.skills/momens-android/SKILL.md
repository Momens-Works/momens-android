---
name: momens-android
description: Use this skill when creating, modifying, or reviewing Android Kotlin and Jetpack Compose code in the Momens project. Applies to Composable UI, ViewModel state, MVVM, Hilt, Navigation, Preview, design system usage, ktlint, editorconfig, and Momens team conventions.
---

# Momens Android Skill

This skill guides Codex to write and review Android Kotlin and Jetpack Compose code according to Momens project conventions.

Use this skill when the task involves:

- Android Kotlin code in the Momens project
- Jetpack Compose UI
- Composable functions
- Android ViewModel
- MVVM structure
- StateFlow or SharedFlow
- Hilt ViewModel
- Navigation
- Route / Screen / Component separation
- Preview
- Design system
- MomensTheme
- ktlint or editorconfig
- UI refactoring
- Compose code review

---

## Reference Files

Before making Compose-related changes, read the relevant reference files.

For general Compose rules, read:

```text
references/compose-basic-flow.md
```

For Momens-specific project rules, read:

```text
references/momens-project-flow.md
```

---

## Core Rules

Always follow these rules:

1. Keep UI and business logic separated.
2. Keep screen state in ViewModel.
3. Use StateFlow for persistent UI state.
4. Use SharedFlow or SideEffect for one-time events.
5. Follow State Down, Event Up.
6. Prefer stateless Composables.
7. Expose `modifier: Modifier = Modifier` in reusable Composables.
8. Do not force `fillMaxSize()` inside reusable components.
9. Do not pass ViewModel or NavController into reusable components.
10. Use `collectAsStateWithLifecycle()` in Route-level Composables.
11. Separate Route, Screen, and Component responsibilities.
12. Use Hilt ViewModel in Route-level Composables.
13. Use MomensTheme and design system components before raw Material components.
14. Use Preview with fake UiState or sample models.
15. Do not use real ViewModel, Repository, or Network in Preview.
16. Use ImmutableList for lists exposed through UiState when possible.
17. Use stable keys in LazyColumn, LazyRow, and grid lists when possible.
18. Avoid exposing API response models directly to UI.
19. Handle Loading, Success, Failure states clearly.
20. Follow ktlint and editorconfig formatting.

---

## Route / Screen / Component Rule

Prefer this structure:

```text
XxxRoute
XxxScreen or XxxContent
component/XxxComponent
```

Responsibilities:

### `XxxRoute`

- Connects ViewModel
- Collects UiState
- Collects SideEffect
- Connects navigation callbacks
- Handles route-level lifecycle logic

### `XxxScreen` or `XxxContent`

- Receives UiState and event lambdas
- Draws UI only
- Does not know ViewModel
- Does not know NavController

### `Component`

- Reusable UI unit
- Receives data and event lambdas
- Does not directly reference ViewModel, Repository, or NavController
- Exposes `modifier: Modifier = Modifier`

---

## When Creating a New Screen

Follow this order:

1. Define `XxxUiState`.
2. Define necessary `XxxUiModel`.
3. Add fake state for Preview.
4. Create `XxxRoute`.
5. Create `XxxScreen`.
6. Split reusable UI into `component/`.
7. Add Preview.
8. Connect navigation through Route.
9. Connect API later through ViewModel and repository mapping.
10. Remove temporary mock data from Composable bodies.

---

## When Reviewing Code

Check:

- Is state owned by ViewModel?
- Is UI stateless where possible?
- Is business logic outside Composable?
- Does Route collect StateFlow with `collectAsStateWithLifecycle()`?
- Are one-time events separated into SideEffect?
- Are reusable components free from ViewModel and NavController?
- Is `modifier` exposed?
- Is `fillMaxSize()` avoided inside components?
- Is MomensTheme used?
- Are common design system components reused?
- Are Preview functions private and themed?
- Are fake states used for Preview?
- Are lists immutable in UiState where possible?
- Are Lazy list keys stable?
- Are Loading and Failure states handled?
- Are ktlint and editorconfig rules followed?

---

## Output Style

When changing code:

1. Briefly explain what files changed.
2. Explain how the change follows Momens Compose rules.
3. Mention any assumptions.
4. Mention any checks that should be run.

When reviewing code:

1. List critical issues first.
2. Then major issues.
3. Then minor issues.
4. Include file paths and symbols.
5. Suggest the smallest practical fix.
6. Avoid vague style-only comments.
