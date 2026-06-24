package ai.runapi.luma.resources;

import ai.runapi.core.ClientOptions;
import ai.runapi.core.RequestOptions;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.polling.TaskCreateResponse;
import ai.runapi.luma.types.CompletedModifyVideoResponse;
import ai.runapi.luma.types.ModifyVideoParams;
import ai.runapi.luma.types.ModifyVideoResponse;

/** Modify Video operations. */
public final class ModifyVideoResource extends LumaResource {
  /** API endpoint path for modify video operations. */
  public static final String ENDPOINT = "/api/v1/luma/modify_video";

  /** Creates a resource bound to the supplied transport and client options. */
  public ModifyVideoResource(HttpTransport transport, ClientOptions options) {
    super(transport, options, ENDPOINT);
  }

  /** Creates a modify video task. */
  public TaskCreateResponse create(ModifyVideoParams params) {
    return create(params, RequestOptions.none());
  }

  /** Creates a modify video task with per-request options. */
  public TaskCreateResponse create(ModifyVideoParams params, RequestOptions options) {
    return createTask(params.action(), params.toMap(), options);
  }

  /** Retrieves a modify video task by ID. */
  public ModifyVideoResponse get(String id) {
    return get(id, RequestOptions.none());
  }

  /** Retrieves a modify video task by ID with per-request options. */
  public ModifyVideoResponse get(String id, RequestOptions options) {
    return getTask(id, options, ModifyVideoResponse.class);
  }

  /** Creates a modify video task and polls until it completes. */
  public CompletedModifyVideoResponse run(ModifyVideoParams params) {
    return run(params, RequestOptions.none());
  }

  /** Creates a modify video task with per-request options and polls until it completes. */
  public CompletedModifyVideoResponse run(ModifyVideoParams params, RequestOptions options) {
    return runTask(params.action(), params.toMap(), options, ModifyVideoResponse.class, CompletedModifyVideoResponse.class);
  }
}
