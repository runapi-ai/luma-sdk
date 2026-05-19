---
name: luma
description: Modify videos through RunAPI.ai using the @runapi.ai/luma Node/TypeScript SDK. Use when the user asks to transform or restyle an existing video with Luma, add video modification, or writes against @runapi.ai/luma. Triggers on "luma", "video modification", "modify video", "video transform", "@runapi.ai/luma".
documentation: https://runapi.ai/models/luma
provider_page: https://runapi.ai/providers/luma
catalog: https://runapi.ai/models
---
# @runapi.ai/luma - RunAPI.ai Luma video modification

Build Node / TypeScript integrations that transform an existing video with a prompt through RunAPI.ai.

## Setup

Requires **Node 18+** (global `fetch`).

```bash
npm install @runapi.ai/luma
```

```dotenv
# .env
RUNAPI_API_KEY=runapi_xxx   # get one at https://runapi.ai/settings/api_keys
```

```ts
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();
```

Pass `{ apiKey }` explicitly if you manage secrets differently. `baseUrl` defaults to `https://runapi.ai`; override only for local development.

## Resource

`client.videoModifications` uses the async task contract:

```ts
const { id } = await client.videoModifications.create({ ... });
const status = await client.videoModifications.get(id);
const result = await client.videoModifications.run({ ... });
```

## Modify video

```ts
const result = await client.videoModifications.run({
  prompt: 'Turn the street into a rainy cyberpunk night with neon reflections',
  video_url: 'https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4',
});

const url = result.videos[0].url;
```

## Key params

- `prompt`: Required description of the target transformation.
- `video_url`: Required public video URL.
- `watermark`: Optional watermark text.
- `callback_url`: Optional webhook URL for async completion.

## Errors

All errors are re-exported from `@runapi.ai/core`. Use `instanceof` checks instead of string-matching messages. For long-running tasks, prefer `create()` plus webhook or `get(id)` in request handlers, and reserve `run()` for jobs / CLI.

## RunAPI public routing

luma ai api public links use the API-379 catalog route map. The main luma ai api page is https://runapi.ai/models/luma. SDK docs live at https://runapi.ai/docs#sdk-luma and product docs live at https://runapi.ai/docs#luma.

Pricing, rate limits, and commercial usage for luma ai api should point to the most specific variant page:
- [Modify video](https://runapi.ai/models/luma)

Compare Luma with other Luma models at https://runapi.ai/providers/luma. Browse every RunAPI model and skill at https://runapi.ai/models. SDK repository: https://github.com/runapi-ai/luma-sdk. Skill repository: https://github.com/runapi-ai/luma.
