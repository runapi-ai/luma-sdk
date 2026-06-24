package ai.runapi.luma.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Parameters for modify video operations. */
public final class ModifyVideoParams {
  private final String model;
  private final String prompt;
  private final String sourceVideoUrl;
  private final String callbackUrl;
  private final Boolean watermark;

  private ModifyVideoParams(Builder builder) {
    this.model = builder.model;
    this.prompt = LumaParamUtils.requireNonBlank(builder.prompt, "prompt");
    this.sourceVideoUrl = LumaParamUtils.requireNonBlank(builder.sourceVideoUrl, "sourceVideoUrl");
    this.callbackUrl = builder.callbackUrl;
    this.watermark = builder.watermark;
  }

  /** Creates a new ModifyVideoParams builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Returns the RunAPI action key for this request. */
  public String action() {
    return "luma/modify-video";
  }

  /** Converts these parameters to the JSON request body shape. */
  public Map<String, Object> toMap() {
    Map<String, Object> raw = new LinkedHashMap<String, Object>();
    raw.put("model", LumaParamUtils.wireValue(model));
    raw.put("prompt", LumaParamUtils.wireValue(prompt));
    raw.put("source_video_url", LumaParamUtils.wireValue(sourceVideoUrl));
    raw.put("callback_url", LumaParamUtils.wireValue(callbackUrl));
    raw.put("watermark", LumaParamUtils.wireValue(watermark));
    return LumaParamUtils.compact(raw);
  }



  /** Builder for {@link ModifyVideoParams}. */
  public static final class Builder {
    private String model;
    private String prompt;
    private String sourceVideoUrl;
    private String callbackUrl;
    private Boolean watermark;

    private Builder() {}

    /** Sets the model slug using a typed model value. */
    public Builder model(ModifyVideoModel value) {
      this.model = java.util.Objects.requireNonNull(value, "model").value();
      return this;
    }

    /** Sets the model slug using a string value. */
    public Builder model(String value) {
      this.model = LumaParamUtils.requireNonBlankTrim(value, "model");
      return this;
    }


    /** Sets the text prompt. */
    public Builder prompt(String value) {
      this.prompt = LumaParamUtils.requireNonBlank(value, "prompt");
      return this;
    }

    /** Sets the source video URL. */
    public Builder sourceVideoUrl(String value) {
      this.sourceVideoUrl = LumaParamUtils.requireNonBlank(value, "sourceVideoUrl");
      return this;
    }

    /** Sets the webhook URL for task completion notifications. */
    public Builder callbackUrl(String value) {
      this.callbackUrl = LumaParamUtils.requireNonBlank(value, "callbackUrl");
      return this;
    }

    /** Sets the watermark toggle. */
    public Builder watermark(boolean value) {
      this.watermark = value;
      return this;
    }

    /** Builds immutable modify video parameters. */
    public ModifyVideoParams build() {
      return new ModifyVideoParams(this);
    }
  }
}
