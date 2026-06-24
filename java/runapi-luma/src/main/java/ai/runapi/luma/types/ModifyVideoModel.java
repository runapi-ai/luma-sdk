package ai.runapi.luma.types;

import com.fasterxml.jackson.annotation.JsonCreator;

/** Model slug for modify video operations. */
public final class ModifyVideoModel extends LumaValue {
  /** luma-modify-video model slug. */
  public static final ModifyVideoModel LUMA_MODIFY_VIDEO = new ModifyVideoModel("luma-modify-video");

  /** Creates a model value from a literal model slug. */
  @JsonCreator
  public ModifyVideoModel(String value) {
    super(value);
  }
}
