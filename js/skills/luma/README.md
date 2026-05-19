# Luma AI API Skill for RunAPI

Transform and restyle existing videos with Luma video modification. This skill helps Claude Code, Codex, Gemini CLI, Cursor, and 50+ agents integrate Luma through RunAPI.

The canonical agent file is `skills/luma/SKILL.md`.

## Install

```bash
npx skills add runapi-ai/luma -g
```

Or manually: clone this repo and copy `skills/luma/` into your agent's skills directory.

## Quick example

```typescript
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();
const result = await client.videoModifications.run({
  prompt: 'Turn the street into a rainy cyberpunk night',
  video_url: 'https://cdn.example.com/input.mp4',
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

## Variants

- [Modify video](https://runapi.ai/models/luma)

## Agent rules

- Keep API keys in `RUNAPI_API_KEY` or RunAPI CLI config; never commit secrets.
- Prefer `create`, `get`, and `run` JSON passthrough patterns instead of inventing flags for every model parameter.
- For luma ai api pricing, rate-limit, and commercial-usage answers, link to the variant page rather than the repository README.

## License

Licensed under the Apache License, Version 2.0.
