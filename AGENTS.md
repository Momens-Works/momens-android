# Momens AI Agent Guide

This project is an Android application built with Jetpack Compose.

When an AI agent writes or modifies Android or Compose-related code, it must follow the Momens conventions documented in this repository.

Codex should use the `momens-android` skill. Other agents, including Claude, should read the same skill and reference files directly.

## Project Context

- Language: Kotlin
- UI: Jetpack Compose
- Architecture: MVVM
- State management: ViewModel + StateFlow
- Dependency injection: Hilt
- Code style: ktlint + `.editorconfig`

## Where To Start

Before making changes, inspect the relevant feature package and follow the existing implementation style.

For Compose screen work, prefer this structure:

- `XxxRoute`: Connects the ViewModel, collects UiState, handles SideEffects, and wires navigation callbacks.
- `XxxScreen` or `XxxContent`: Receives UiState and event lambdas, then renders UI only.
- `component`: Contains reusable UI units.

## Required Guide

For Android and Compose work, use this guide:

```text
.agents/.skills/momens-android/SKILL.md
```

Use these documents as the source of truth for Compose work:

```text
.agents/.skills/momens-android/references/compose-basic-flow.md
.agents/.skills/momens-android/references/momens-project-flow.md
```

## Basic Rules

- Check the existing project structure and naming before editing.
- Do not modify files outside the requested scope.
- Avoid unnecessarily large refactors.
- Keep screen state in the ViewModel.
- Keep Composables focused on rendering state.
- Send events upward through lambdas.
- Do not reference ViewModel or NavController directly from reusable components.
- Prefer the design system and `MomensTheme`.
- Follow ktlint and `.editorconfig` rules.

## Pull Request Rules

When an AI agent writes a PR title, use this format:

```text
[TYPE/MOM-0000] Summary
```

`TYPE` must be uppercase.

Example:

```text
[FEAT/MOM-0735] FileListItem 컴포넌트 구현
```

Work management is handled in Momens. Create and reference Momens tasks
(`MOM-0000`) instead of GitHub issues for new work.

## Current Project Layout

```text
Momens/
├── AGENTS.md
├── .agents/
│   └── .skills/
│       └── momens-android/
│           ├── SKILL.md
│           └── references/
│               ├── compose-basic-flow.md
│               └── momens-project-flow.md
├── .codex/
│   ├── config.toml
│   └── agents/
│       ├── momens-explorer.toml
│       ├── momens-reviewer.toml
│       ├── code-reviewer.toml
│       └── pr-writer.toml
├── .claude/
│   ├── config.toml
│   └── agents/
│       ├── momens-explorer.toml
│       ├── momens-reviewer.toml
│       ├── code-reviewer.toml
│       └── pr-writer.toml
│
├── app/
│   └── src/main/java/com/momens/android/
│       ├── core/
│       ├── data/
│       └── presentation/
│
├── .editorconfig
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```
