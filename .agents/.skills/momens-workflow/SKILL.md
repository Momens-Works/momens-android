---
name: momens-workflow
description: Use this skill when managing Momens Android collaboration workflow: creating or checking Momens tasks, starting work branches, applying MOM-0000 branch/commit/PR conventions, creating pull requests, updating Momens task comments/status, or migrating GitHub issues/PRs into Momens. Trigger on natural requests like "티켓 파고 브랜치 만들어줘", "컨벤션 맞춰 커밋하고 PR 만들어줘", "Momens 티켓에 기록 남겨줘", or "GitHub 이슈를 Momens로 이관해줘".
---

# Momens Workflow Skill

This skill keeps Momens Android collaboration work consistent across Codex and Claude Code.

Use it for workflow and task-management steps. For Android or Compose implementation details, also use `momens-android`.

## User Context

Assume the user is Korean and may be new to Momens, Jira, Linear, GitHub issue workflows, CLI commands, and LLM-assisted development.

The user may ask in plain Korean instead of naming the exact command or tool. Treat these as workflow requests:

```text
티켓 하나 파고 브랜치 만들어줘.
MOM-0752 작업 시작해줘.
컨벤션 맞춰서 커밋하고 PR 만들어줘.
PR 본문도 알아서 써줘.
작업 끝났으니 Momens 티켓에 기록 남겨줘.
GitHub 이슈를 Momens로 옮겨줘.
```

When responding, avoid assuming the user knows what a task, branch, commit, or PR is. Briefly name the concrete artifact created and why it matters.

## Core Rules

1. Momens is the source of truth for work tracking.
2. New work must use a Momens task label in the form `MOM-0000`.
3. Do not create GitHub issues for normal project work.
4. GitHub is still used for branches, commits, pull requests, CI, and review.
5. Preserve GitHub issue/PR links only as historical references when migrating old work.
6. Do not invent task labels, assignees, test results, PR URLs, or issue links.
7. Record important workflow actions as comments on the Momens task.

## Naming Conventions

### Branch

Use:

```text
type/MOM-0000-short-summary
```

Examples:

```text
docs/MOM-0752-momens-task-workflow
feat/MOM-0735-input-limit
fix/MOM-0733-people-list-image
```

### Commit

Use:

```text
[TYPE/MOM-0000] Summary
```

Examples:

```text
[DOCS/MOM-0752] 작업 관리 기준 Momens 전환
[FEAT/MOM-0735] Input 글자 수 제한 추가
```

### Pull Request Title

Use the same format as commits:

```text
[TYPE/MOM-0000] Summary
```

### Pull Request Body

Follow `.github/pull_request_template.md`.

Set the related task section like:

```text
- Momens: MOM-0000
```

Do not use `closed #123` for normal work.

## Start Work Flow

When the user asks to start work, create a ticket, or make a branch:

1. Check the current branch and working tree.
2. If there are uncommitted changes, do not overwrite them. Explain the situation and continue only when safe.
3. Find the correct Momens project and milestone if needed.
4. Create or identify the Momens task.
5. Set the task to `in_progress` when starting active work.
6. Create a branch using `type/MOM-0000-short-summary`.
7. Comment on the Momens task with the branch name and intended scope.

Prefer these Momens MCP tools when available:

- `list_projects`
- `list_milestones`
- `list_tasks`
- `get_task`
- `create_task`
- `update_task`
- `create_comment`

If Momens MCP is unavailable, ask the user for the Momens task label and continue with branch/PR conventions.

## Commit Flow

When the user asks to commit:

1. Confirm the branch contains a `MOM-0000` label.
2. Check `git status --short --branch`.
3. Run lightweight relevant checks before committing.
   - For docs/config-only changes, run `git diff --check`.
   - For Android code changes, prefer project checks such as ktlint/build when feasible.
4. Stage only files in scope.
5. Commit with `[TYPE/MOM-0000] Summary`.
6. Comment on the Momens task with the commit hash and validation performed.

## Pull Request Flow

When the user asks to create a PR:

1. Push the current branch.
2. Create the PR against `develop` unless the user specifies another base.
3. Use title format `[TYPE/MOM-0000] Summary`.
4. Fill `.github/pull_request_template.md`.
5. Include the Momens task label in `Related Task`.
6. Mention only validation that actually ran.
7. Comment on the Momens task with the PR URL.

## GitHub Migration Flow

Use this only for old GitHub issue/PR migration.

1. Read the GitHub issue and linked PRs.
2. Create a Momens task with the original GitHub links in the description.
3. Use GitHub state to choose initial Momens status:
   - closed issue -> `done`
   - open issue with open PR -> `in_progress`
   - open issue without PR -> `todo`
4. Comment on the GitHub issue:

```text
이 이슈는 Momens로 이관되었습니다.

- Momens 티켓: MOM-0000
- 이관 마일스톤: <milestone>
- GitHub 이슈는 기록 보존용으로 유지합니다.
```

5. Comment on linked GitHub PRs:

```text
관련 GitHub 이슈가 Momens로 이관되었습니다.

- Momens 티켓: MOM-0000
- 이관 마일스톤: <milestone>
- 원본 이슈: #123
- 이 PR은 기록 보존 및 리뷰 추적용으로 유지합니다.
```

## Finish Work Flow

When work is complete:

1. Confirm the PR is merged or the requested deliverable is actually complete.
2. Comment on the Momens task with the final PR/commit/check summary.
3. Set the Momens task to `done` only when the work is complete.
4. If blocked, set status to `todo` or leave `in_progress` and add a clear blocker comment.

## Output Style

When reporting back to the user:

1. State the Momens task label.
2. State the branch, commit, or PR URL if created.
3. State checks that ran.
4. Mention any skipped or failed step plainly.
