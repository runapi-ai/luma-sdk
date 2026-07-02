# Luma API JavaScript SDK for RunAPI

The Luma JavaScript SDK is the language-specific package for Luma on RunAPI. Use this package for video generation, animation, and video editing workflows when your application needs request bodies, task status lookup, and consistent RunAPI errors in JavaScript.

This README is the JavaScript package guide inside the public `luma-sdk` repository. For the repository overview, start at `../README.md`; for model details, use https://runapi.ai/models/luma; for API reference, use https://runapi.ai/docs#luma; for SDK docs, use https://runapi.ai/docs#sdk-luma.

## Install

```bash
npm install @runapi.ai/luma
```

## Quick start

```typescript
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();
const task = await client.modifyVideo.create({
  // Pass the Luma JSON request body from https://runapi.ai/docs#luma.
});
const status = await client.modifyVideo.get(task.id);
```

Use `create` when you want to submit a task and return quickly, `get` when you need the latest task state, and `run` when a script should create and poll until completion. In web request handlers, prefer `create` plus webhook or later `get` polling so a worker is not held open.

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## Language notes

Use the TypeScript types in `src/types.ts` and the resource classes under `src/resources` when building video applications. The available resources are `modifyVideo`. Keep `RUNAPI_API_KEY` in the environment or your secret manager; never commit API keys or callback secrets.

## Links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs#sdk-luma
- Product docs: https://runapi.ai/docs#luma
- Pricing and rate limits: https://runapi.ai/models/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models
- Repository: https://github.com/runapi-ai/luma-sdk

## License

Licensed under the Apache License, Version 2.0.
