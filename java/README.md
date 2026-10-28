# Luma Java SDK for RunAPI

[![Maven Central](https://img.shields.io/maven-central/v/ai.runapi/runapi-luma)](https://central.sonatype.com/artifact/ai.runapi/runapi-luma)

Luma Modify Video (`luma-modify-video`) was retired on 2026-10-14. New requests return HTTP 410 before task creation or balance deduction. The old ID does not map to a replacement. This package remains available for historical task lookup.

This README is the Java package guide inside the public `luma-sdk` repository. For the repository overview, start at `../README.md`; for model details, use https://runapi.ai/models/luma; for API reference, use https://runapi.ai/docs/api/luma/modify-video; for SDK docs, use https://runapi.ai/docs/resources/sdks.

## Requirements

The Java SDK targets Java 8 bytecode and is tested on Java 8, 11, 17, and 21.

## Install

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

Use the BOM when multiple RunAPI Java modules are installed:

```kotlin
dependencies {
  implementation(platform("ai.runapi:runapi-bom:0.10.0"))
  implementation("ai.runapi:runapi-luma")
}
```

Maven BOM:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>ai.runapi</groupId>
      <artifactId>runapi-bom</artifactId>
      <version>0.10.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

## Historical task lookup

```java
import ai.runapi.luma.LumaClient;
import ai.runapi.luma.types.ModifyVideoResponse;

LumaClient client = LumaClient.builder()
    .apiKey(System.getenv("RUNAPI_API_KEY"))
    .build();

ModifyVideoResponse status = client.modifyVideo().get("existing-task-id");
```

The client builder reads `RUNAPI_API_KEY` when `.apiKey(...)` is omitted. Use `get` for an existing task ID. Luma has no callable model for new tasks.

RunAPI-generated file URLs are temporary. Store files from historical results in your own durable storage.

## Links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/luma/modify-video
- Retirement reference: https://runapi.ai/models/luma
- Full catalog: https://runapi.ai/models
- Repository: https://github.com/runapi-ai/luma-sdk

## License

Licensed under the Apache License, Version 2.0.
