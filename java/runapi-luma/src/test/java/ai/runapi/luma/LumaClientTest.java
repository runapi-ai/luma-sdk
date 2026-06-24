package ai.runapi.luma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ai.runapi.core.RequestOptions;
import ai.runapi.core.errors.ValidationException;
import ai.runapi.core.http.HttpRequest;
import ai.runapi.core.http.HttpResponse;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.http.JsonRequestBody;
import ai.runapi.core.json.Json;
import ai.runapi.luma.types.CompletedModifyVideoResponse;
import ai.runapi.luma.types.ModifyVideoResponse;
import ai.runapi.luma.types.CompletedModifyVideoResponse;
import ai.runapi.luma.types.ModifyVideoModel;
import ai.runapi.luma.types.ModifyVideoParams;
import ai.runapi.luma.types.ModifyVideoResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class LumaClientTest {
  @Test
  void builderCreatesClientAndUniversalResources() {
    LumaClient client = LumaClient.builder().apiKey("sk-test").build();

    assertNotNull(client.modifyVideo());
    assertNotNull(client.files());
    assertNotNull(client.account());
  }

  @Test
  void openValueClassesSerializeAsScalarStrings() throws Exception {
    String json = Json.mapper().writeValueAsString(new ModifyVideoModel("luma-modify-video"));

    assertEquals("\"luma-modify-video\"", json);
    assertEquals(new ModifyVideoModel("luma-modify-video"), Json.mapper().readValue(json, ModifyVideoModel.class));
  }

  @Test
  void createSendsExpectedRequestShape() throws Exception {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"task_123\",\"status\":\"processing\"}");
    LumaClient client = LumaClient.builder().apiKey("sk-test").transport(transport).build();

    client.modifyVideo().create(
        ModifyVideoParams.builder()
            .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
            .prompt("A small red cube on a plain white table, studio product photo")
            .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
            .build()
    );

    assertEquals("POST", transport.request.getMethod().name());
    assertEquals("/api/v1/luma/modify_video", transport.request.getPath());
    JsonNode body = bodyJson(transport.request);
    assertNotNull(body);
  }

  @Test
  void getDecodesTaskResponseAndExtraFields() {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"task_456\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}],\"custom\":\"kept\"}");
    LumaClient client = LumaClient.builder().apiKey("sk-test").transport(transport).build();

    ModifyVideoResponse response = client.modifyVideo().get("task_456");

    assertEquals("GET", transport.request.getMethod().name());
    assertEquals("/api/v1/luma/modify_video/task_456", transport.request.getPath());
    assertEquals("completed", response.getStatus().value());
    assertNotNull(response.getVideos());
    assertEquals("kept", response.extraFields().get("custom").asText());
  }

  @Test
  void runPollsUntilCompletedAndKeepsExtraFields() {
    SequenceTransport transport = new SequenceTransport(
        "{\"id\":\"task_789\",\"status\":\"processing\"}",
        "{\"id\":\"task_789\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}],\"custom\":\"kept\"}");
    LumaClient client = LumaClient.builder().apiKey("sk-test").transport(transport).build();

    CompletedModifyVideoResponse response = client.modifyVideo().run(
        ModifyVideoParams.builder()
            .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
            .prompt("A small red cube on a plain white table, studio product photo")
            .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
            .build(),
        RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build());

    assertEquals("completed", response.getStatus().value());
    assertNotNull(response.getVideos());
    assertEquals("kept", response.extraFields().get("custom").asText());
    assertEquals(2, transport.calls);
  }

  @Test
  void runRejectsCompletedResponseMissingResultField() {
    SequenceTransport transport = new SequenceTransport(
        "{\"id\":\"task_missing\",\"status\":\"processing\"}",
        "{\"id\":\"task_missing\",\"status\":\"completed\"}");
    LumaClient client = LumaClient.builder().apiKey("sk-test").transport(transport).build();

    assertThrows(
        ValidationException.class,
        () -> client.modifyVideo().run(
                ModifyVideoParams.builder()
                    .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
                    .prompt("A small red cube on a plain white table, studio product photo")
                    .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
                    .build(),
            RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build()));
  }

    @Test
    void coversModifyvideoResourceMethods() {
      CapturingTransport createTransport = new CapturingTransport("{\"id\":\"task_modify_video\",\"status\":\"processing\"}");
      LumaClient createClient = LumaClient.builder().apiKey("sk-test").transport(createTransport).build();
      assertNotNull(createClient.modifyVideo().create(
              ModifyVideoParams.builder()
                  .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
                  .prompt("A small red cube on a plain white table, studio product photo")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
                  .build()
      ));

      CapturingTransport createWithOptionsTransport = new CapturingTransport("{\"id\":\"task_modify_video_options\",\"status\":\"processing\"}");
      LumaClient createWithOptionsClient = LumaClient.builder().apiKey("sk-test").transport(createWithOptionsTransport).build();
      assertNotNull(createWithOptionsClient.modifyVideo().create(
              ModifyVideoParams.builder()
                  .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
                  .prompt("A small red cube on a plain white table, studio product photo")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
                  .build(),
          RequestOptions.none()));

      CapturingTransport getTransport = new CapturingTransport("{\"id\":\"task_modify_video\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      LumaClient getClient = LumaClient.builder().apiKey("sk-test").transport(getTransport).build();
      assertNotNull(getClient.modifyVideo().get("task_modify_video"));

      CapturingTransport getWithOptionsTransport = new CapturingTransport("{\"id\":\"task_modify_video_options\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      LumaClient getWithOptionsClient = LumaClient.builder().apiKey("sk-test").transport(getWithOptionsTransport).build();
      assertNotNull(getWithOptionsClient.modifyVideo().get("task_modify_video_options", RequestOptions.none()));

      SequenceTransport runTransport = new SequenceTransport(
          "{\"id\":\"task_modify_video_run\",\"status\":\"processing\"}",
          "{\"id\":\"task_modify_video_run\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      LumaClient runClient = LumaClient.builder().apiKey("sk-test").transport(runTransport).build();
      CompletedModifyVideoResponse runResponse = runClient.modifyVideo().run(
              ModifyVideoParams.builder()
                  .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
                  .prompt("A small red cube on a plain white table, studio product photo")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
                  .build(),
          RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build());
      assertNotNull(runResponse);

      SequenceTransport runWithOptionsTransport = new SequenceTransport(
          "{\"id\":\"task_modify_video_run_options\",\"status\":\"processing\"}",
          "{\"id\":\"task_modify_video_run_options\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      LumaClient runWithOptionsClient = LumaClient.builder().apiKey("sk-test").transport(runWithOptionsTransport).build();
      assertNotNull(runWithOptionsClient.modifyVideo().run(
              ModifyVideoParams.builder()
                  .model(ModifyVideoModel.LUMA_MODIFY_VIDEO)
                  .prompt("A small red cube on a plain white table, studio product photo")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/video.mp4")
                  .build(),
          RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build()));
    }

  private static JsonNode bodyJson(HttpRequest request) throws Exception {
    JsonRequestBody body = (JsonRequestBody) request.getBody();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    body.writeTo(out);
    return Json.mapper().readTree(out.toByteArray());
  }

  private static final class CapturingTransport implements HttpTransport {
    private final String body;
    private HttpRequest request;

    private CapturingTransport(String body) {
      this.body = body;
    }

    public HttpResponse send(HttpRequest request) {
      this.request = request;
      return new HttpResponse(200, body, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }

  private static final class SequenceTransport implements HttpTransport {
    private final String[] responses;
    private int calls;

    private SequenceTransport(String... responses) {
      this.responses = responses;
    }

    public HttpResponse send(HttpRequest request) {
      String response = responses[Math.min(calls, responses.length - 1)];
      calls++;
      return new HttpResponse(200, response, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }
}
