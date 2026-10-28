<p align="center">
  <a href="https://github.com/runapi-ai/luma">
    <h3 align="center">Luma AI API Skill for RunAPI</h3>
  </a>
</p>

<p align="center">
  Use this agent skill to look up historical Luma tasks.
</p>

<p align="center">
  <a href="https://runapi.ai/models/luma"><strong>Model Reference</strong></a> · <a href="https://github.com/runapi-ai/cli"><strong>CLI</strong></a> · <a href="https://github.com/runapi-ai/luma-sdk"><strong>SDK</strong></a>
</p>

<div align="center">

[![skills.sh](https://www.skills.sh/b/runapi-ai/luma)](https://www.skills.sh/runapi-ai/luma/luma)
[![ClawHub](https://img.shields.io/badge/ClawHub-runapi--luma-111827)](https://clawhub.ai/runapi-ai/runapi-luma)
[![License](https://img.shields.io/github/license/runapi-ai/luma)](https://github.com/runapi-ai/luma/blob/main/LICENSE)

</div>
<br/>

Luma Modify Video (`luma-modify-video`) was retired on 2026-10-14. New requests return HTTP 410 before task creation or balance deduction. The old model ID does not map to a replacement. Historical task IDs remain queryable.

The canonical agent file is `skills/luma/SKILL.md`.

## Install

Use this skill with Claude Code, Codex, or Gemini CLI to query historical tasks.

```bash
npx skills add runapi-ai/luma -g
```

Or paste this prompt to your AI agent:

```text
Install the luma skill for me:

1. Clone https://github.com/runapi-ai/luma
2. Copy the skills/luma/ directory into your
   user-level skills directory (e.g. ~/.claude/skills/
   for Claude Code, ~/.codex/skills/ for Codex).
3. Verify that SKILL.md is present.
4. Confirm the install path when done.
```

## Historical task lookup

```typescript
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();
const task = await client.modifyVideo.get('existing-task-id');
```

## Routing

- Model page: https://runapi.ai/models/luma
- Product docs: https://runapi.ai/docs/api/luma/modify-video
- SDK docs: https://runapi.ai/docs/resources/sdks
- SDK repository: https://github.com/runapi-ai/luma-sdk
- Retirement reference: https://runapi.ai/models/luma
- Provider comparison: https://runapi.ai/providers/luma
- Browse all RunAPI models and skills: https://runapi.ai/models

## Agent rules

- Use the target language SDK to query existing task IDs. Luma has no callable model for new tasks.
- RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.
- Keep API keys in `RUNAPI_API_KEY` or RunAPI CLI config; never commit secrets.
- Use `get` for historical tasks. Do not use `create` or `run` to submit Luma work.
- Link to the model page for the retirement date and historical model identity.

## License

Licensed under the Apache License, Version 2.0.
