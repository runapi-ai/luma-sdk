package ai.runapi.luma.types;

import ai.runapi.core.types.RunApiValue;

abstract class LumaValue extends RunApiValue {
  LumaValue(String value) {
    super(value);
  }
}
