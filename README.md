<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center">
  <a href="https://github.com/runapi-ai/luma-sdk">Luma API SDK for RunAPI</a>
</h3>

<p align="center">
  Luma API SDKs for JavaScript, Python, Ruby, Go, and Java on RunAPI.
</p>

<div align="center">

[![npm](https://img.shields.io/npm/v/@runapi.ai/luma)](https://www.npmjs.com/package/@runapi.ai/luma)
[![PyPI](https://img.shields.io/pypi/v/runapi-luma)](https://pypi.org/project/runapi-luma/)
[![RubyGems](https://img.shields.io/gem/v/runapi-luma)](https://rubygems.org/gems/runapi-luma)
[![Go Reference](https://pkg.go.dev/badge/github.com/runapi-ai/luma-sdk/go.svg)](https://pkg.go.dev/github.com/runapi-ai/luma-sdk/go)
[![Maven Central](https://img.shields.io/maven-central/v/ai.runapi/runapi-luma)](https://central.sonatype.com/artifact/ai.runapi/runapi-luma)
[![License](https://img.shields.io/github/license/runapi-ai/luma-sdk)](https://github.com/runapi-ai/luma-sdk/blob/main/LICENSE)

</div>
<br/>

The Luma API SDK packages JavaScript, Python, Ruby, Go, and Java clients for Luma on RunAPI. Use it for video modification workflows when your app needs typed request builders, predictable task polling, file upload helpers, account helpers, and consistent RunAPI errors.

Luma is listed in the RunAPI model catalog at https://runapi.ai/models/luma. Variant pages below carry pricing, rate-limit, and commercial-usage details. The public `luma-sdk` repository groups the language packages, examples, CI, and release tags for this model.

## Install

```bash
npm install @runapi.ai/luma
pip install runapi-luma
gem install runapi-luma
go get github.com/runapi-ai/luma-sdk/go@latest
```

Gradle:

```kotlin
dependencies {
  implementation("ai.runapi:runapi-luma:0.1.0")
}
```

Maven:

```xml
<dependency>
  <groupId>ai.runapi</groupId>
  <artifactId>runapi-luma</artifactId>
  <version>0.1.0</version>
</dependency>
```

Use the Java BOM when installing multiple RunAPI Java modules:

```kotlin
dependencies {
  implementation(platform("ai.runapi:runapi-bom:0.1.0"))
  implementation("ai.runapi:runapi-luma")
}
```

## What you can build

- Build apps, agent workflows, batch jobs, and production services around Luma requests.
- Install only the language package your app needs while keeping one model-specific repository for docs and releases.
- Use `create` for submit-only jobs, `get` for status lookup, and `run` for submit-and-poll scripts.
- Upload local files, URL files, or base64 files through shared RunAPI file helpers.
- Handle validation, authentication, rate limits, insufficient credits, task failures, and polling timeouts through RunAPI SDK errors.

## Java quick start

```java
import ai.runapi.luma.LumaClient;
import ai.runapi.luma.types.ModifyVideoParams;
import ai.runapi.luma.types.CompletedModifyVideoResponse;
import ai.runapi.luma.types.ModifyVideoModel;

LumaClient client = LumaClient.builder()
    .apiKey(System.getenv("RUNAPI_API_KEY"))
    .build();

CompletedModifyVideoResponse result = client.modifyVideo().run(
    ModifyVideoParams.builder()
        .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
        .prompt("Make the scene warmer and add gentle camera movement")
        .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
        .build()
);
```

Java packages target Java 8 bytecode and are tested on Java 8, 11, 17, and 21. Each model artifact depends on `ai.runapi:runapi-core`, so application code normally installs only `ai.runapi:runapi-luma`.

## Task lifecycle

Most media endpoints are asynchronous. `create()` submits a task and returns its id, `get(id)` fetches the latest task state, and `run(params)` creates the task and polls until it reaches a terminal state. In web request handlers, prefer `create()` plus webhook or later `get()` polling so the server does not hold a worker open.

## Repository layout

- `js/` publishes `@runapi.ai/luma`.
- `python/` publishes `runapi-luma`.
- `ruby/` publishes `runapi-luma`.
- `go/` publishes `github.com/runapi-ai/luma-sdk/go`.
- `java/` publishes `ai.runapi:runapi-luma` and uses `ai.runapi:runapi-core`.

## Public links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs#sdk-luma
- Product docs: https://runapi.ai/docs#luma
- SDK repository: https://github.com/runapi-ai/luma-sdk
- Skill repository: https://github.com/runapi-ai/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models

## Pricing and variants

Use the most specific Luma variant page for pricing, rate limits, and commercial usage:
- [Modify video](https://runapi.ai/models/luma)

Default pricing link for the Luma SDK: https://runapi.ai/models/luma

## File storage

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## FAQ

### Which package should I install for Luma work?

Install the model package for your language: `@runapi.ai/luma` on npm, `runapi-luma` on PyPI, `runapi-luma` on RubyGems, `github.com/runapi-ai/luma-sdk/go`, or `ai.runapi:runapi-luma`. Install core SDK packages only when you are building shared SDK infrastructure.

### Where should public links point?

Primary Luma links point to https://runapi.ai/models/luma. Pricing and usage-policy links point to variant pages such as https://runapi.ai/models/luma. Provider comparisons point to https://runapi.ai/providers/luma, and broad browsing points to https://runapi.ai/models.

## License

Licensed under the Apache License, Version 2.0.
