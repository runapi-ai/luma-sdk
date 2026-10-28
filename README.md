<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center">
  <a href="https://github.com/runapi-ai/luma-sdk">Luma API SDK for RunAPI</a>
</h3>

<p align="center">
  Luma API SDKs for JavaScript, Python, Ruby, Go, Java, and PHP on RunAPI.
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

Luma Modify Video (`luma-modify-video`) was retired on 2026-10-14. New requests return HTTP 410 before a task is created or any balance is deducted. The old model ID does not map to a replacement. These packages remain available for historical task lookup.

The retirement reference is https://runapi.ai/models/luma. The public `luma-sdk` repository groups the non-PHP language packages, examples, CI, and release tags. The PHP package is released from a split Composer repository.

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
  implementation("ai.runapi:runapi-luma:0.2.0")
}
```

Maven:

```xml
<dependency>
  <groupId>ai.runapi</groupId>
  <artifactId>runapi-luma</artifactId>
  <version>0.2.0</version>
</dependency>
```

Use the Java BOM when installing multiple RunAPI Java modules:

```kotlin
dependencies {
  implementation(platform("ai.runapi:runapi-bom:0.10.0"))
  implementation("ai.runapi:runapi-luma")
}
```

The PHP package is published from the split Composer repository as `runapi-ai/luma`; see https://github.com/runapi-ai/luma-php for PHP install and examples.

## Historical task lookup

Use an existing task ID with `get`. Luma has no callable model for new tasks. Do not use `create` or `run` to submit work.

```java
import ai.runapi.luma.LumaClient;
import ai.runapi.luma.types.ModifyVideoResponse;

LumaClient client = LumaClient.builder()
    .apiKey(System.getenv("RUNAPI_API_KEY"))
    .build();

ModifyVideoResponse task = client.modifyVideo().get("existing-task-id");
```

Java packages target Java 8 bytecode and are tested on Java 8, 11, 17, and 21. Each model artifact depends on `ai.runapi:runapi-core`, so application code normally installs only `ai.runapi:runapi-luma`.

## Repository layout

- `js/` publishes `@runapi.ai/luma`.
- `python/` publishes `runapi-luma`.
- `ruby/` publishes `runapi-luma`.
- `go/` publishes `github.com/runapi-ai/luma-sdk/go`.
- `java/` publishes `ai.runapi:runapi-luma` and uses `ai.runapi:runapi-core`.

## Public links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/luma/modify-video
- SDK repository: https://github.com/runapi-ai/luma-sdk
- PHP package repository: https://github.com/runapi-ai/luma-php
- Skill repository: https://github.com/runapi-ai/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models

## Retirement reference

[Modify Video](https://runapi.ai/models/luma) retains the model identity and retirement date for historical reference. There is no purchasable Luma variant.

## File storage

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## FAQ

### Which package should I install for Luma work?

Install the model package for your language: `@runapi.ai/luma` on npm, `runapi-luma` on PyPI, `runapi-luma` on RubyGems, `github.com/runapi-ai/luma-sdk/go`, `ai.runapi:runapi-luma` on Maven Central, or `runapi-ai/luma` on Packagist. Install core SDK packages only when you are building shared SDK infrastructure.

### Where should public links point?

Primary Luma links point to https://runapi.ai/models/luma. That page records the retirement and historical model identity. Provider comparisons point to https://runapi.ai/providers/luma, and broad browsing points to https://runapi.ai/models.

## License

Licensed under the Apache License, Version 2.0.
