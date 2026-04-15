---
name: update-marketplace-changelog
description: Populate the JetBrains Marketplace "What's New" section in patchPluginXml by reading CHANGELOG.md and syncing the latest entries into build.gradle.kts changeNotes. Use whenever the user wants to update the marketplace release notes, sync the changelog, or says "update marketplace changelog", "populate what's new", "sync release notes".
allowed-tools: Read, Edit, Bash, Agent
---

# Update Marketplace Changelog

Sync the latest entries from `changelog/CHANGELOG.md` into the `changeNotes.set(...)` block inside `patchPluginXml` in `build.gradle.kts`.

## Process

### Step 1: Read the changelog

Read `changelog/CHANGELOG.md` and extract the **3 most recent version entries** (everything from `# <version>` down to the next `# <version>` heading).

### Step 2: Convert to HTML

Convert each entry to HTML for the `changeNotes` block:
- Version heading (`# 88.5-x.y.z [label]`) → `<h3>version — label</h3>`
- Bullet items (`- text`) → `<li>text</li>` inside `<ul>...</ul>`
- Inline code (backticks) → `<code>...</code>`

### Step 3: Update build.gradle.kts

Read `build.gradle.kts` and replace the content of `changeNotes.set("""...""".trimIndent())` inside the `patchPluginXml` block with the new HTML.

If `changeNotes.set(...)` does not exist yet, add it inside `patchPluginXml { ... }` after any existing lines.

### Step 4: Commit and push

Use the `commit` skill with argument `and push` to stage and push the change.
Suggested commit message type: `chore`, scope: `marketplace`.
