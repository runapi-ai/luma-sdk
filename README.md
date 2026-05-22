<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center">
  <a href="https://github.com/runapi-ai/luma-sdk">Luma API SDK for RunAPI</a>
</h3>

<p align="center">
  Luma API SDKs for JavaScript, Ruby, and Go on RunAPI.
</p>

<div align="center">

[![npm](https://img.shields.io/npm/v/@runapi.ai/luma)](https://www.npmjs.com/package/@runapi.ai/luma)
[![RubyGems](https://img.shields.io/gem/v/runapi-luma)](https://rubygems.org/gems/runapi-luma)
[![Go Reference](https://pkg.go.dev/badge/github.com/runapi-ai/luma-sdk/go.svg)](https://pkg.go.dev/github.com/runapi-ai/luma-sdk/go)
[![License](https://img.shields.io/github/license/runapi-ai/luma-sdk)](https://github.com/runapi-ai/luma-sdk/blob/main/LICENSE)

</div>
<br/>

The luma ai api SDK packages JavaScript, Ruby, and Go clients for Luma on RunAPI. Use this luma ai api SDK for video modification and transformation workflows that need typed installs, JSON request bodies, task polling, and consistent RunAPI errors across services.

Luma belongs to the Luma catalog on RunAPI. The public model page is https://runapi.ai/models/luma; variant pages below carry pricing, rate-limit, and commercial-usage details. The public `luma-sdk` repository groups the JavaScript, Ruby, and Go packages for this model.

## Install

```bash
npm install @runapi.ai/luma
gem install runapi-luma
go get github.com/runapi-ai/luma-sdk/go@latest
```

## What you can build

- Build creative tools, agent pipelines, and production integrations with the luma ai api SDK.
- Keep one model-specific repository while installing only the language package your app needs.
- Use `create` for submit-only jobs, `get` for status lookup, and `run` for submit-and-poll scripts.
- Handle authentication, validation, rate limits, insufficient credits, task failures, and polling timeouts through RunAPI SDK errors.

The JavaScript client exposes modify video resources, and the Ruby and Go packages mirror the same RunAPI task lifecycle.

## JavaScript quick start

```typescript
import { LumaClient } from '@runapi.ai/luma';

const client = new LumaClient();

const task = await client.modifyVideo.create({
  // Pass the Luma request body documented at https://runapi.ai/docs#luma.
});

const status = await client.modifyVideo.get(task.id);
```

For short scripts, use `run` with the same JSON body to create the task and wait for completion. For web request handlers, prefer `create` plus webhook or later `get` polling so the server does not hold a worker open.

## Repository layout

- `js/` publishes `@runapi.ai/luma`.
- `ruby/` publishes `runapi-luma` when RubyGems publishing resumes.
- `go/` publishes `github.com/runapi-ai/luma-sdk/go` and depends on `github.com/runapi-ai/core-sdk/go`.

## Public links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs#sdk-luma
- Product docs: https://runapi.ai/docs#luma
- SDK repository: https://github.com/runapi-ai/luma-sdk
- Skill repository: https://github.com/runapi-ai/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models

## Pricing and variants

Use the most specific luma ai api variant page for pricing, rate limits, and commercial usage:
- [Modify video](https://runapi.ai/models/luma)

Default pricing link for the luma ai api SDK: https://runapi.ai/models/luma

## FAQ

### Which package should I install for luma ai api work?

Install the model package for your language: `@runapi.ai/luma`, `runapi-luma`, or `github.com/runapi-ai/luma-sdk/go`. Install core SDK packages only when you are building shared SDK infrastructure.

### Where should public links point?

Primary luma ai api links point to https://runapi.ai/models/luma. Pricing and usage-policy links point to variant pages such as https://runapi.ai/models/luma. Provider comparisons point to https://runapi.ai/providers/luma, and broad browsing points to https://runapi.ai/models.

## License

Licensed under the Apache License, Version 2.0.
