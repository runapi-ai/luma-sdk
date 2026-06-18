<p align="center">
  <a href="https://github.com/runapi-ai/luma">
    <h3 align="center">Luma AI API Skill for RunAPI</h3>
  </a>
</p>

<p align="center">
  Install this agent skill, inspect Luma fields, then run jobs through the RunAPI CLI.
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

Transform and restyle existing videos with Luma video modification. This skill helps Claude Code, Codex, Gemini CLI, Cursor, and 50+ agents integrate Luma through RunAPI.

The canonical agent file is `skills/luma/SKILL.md`.

## Install

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

## Quick example

```typescript
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();
const result = await client.videoModifications.run({
  prompt: 'Turn the street into a rainy cyberpunk night',
  source_video_url: 'https://cdn.runapi.ai/public/samples/video.mp4',
});
const url = result.videos[0].url;
```

## Routing

- Model page: https://runapi.ai/models/luma
- Product docs: https://runapi.ai/docs#luma
- SDK docs: https://runapi.ai/docs#sdk-luma
- SDK repository: https://github.com/runapi-ai/luma-sdk
- Pricing and rate limits: https://runapi.ai/models/luma
- Provider comparison: https://runapi.ai/providers/luma
- Browse all RunAPI models and skills: https://runapi.ai/models

## Agent rules

- Integration work uses the target language SDK; one-off generation, manual smoke tests, debugging, or user-requested CLI runs use the RunAPI CLI skill: https://github.com/runapi-ai/cli-skill
- RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.
- Keep API keys in `RUNAPI_API_KEY` or RunAPI CLI config; never commit secrets.
- Prefer `create`, `get`, and `run` JSON passthrough patterns instead of inventing flags for every model parameter.
- For luma ai api pricing, rate-limit, and commercial-usage answers, link to the model page rather than the repository README.

## License

Licensed under the Apache License, Version 2.0.
