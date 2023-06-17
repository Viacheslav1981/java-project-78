package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    public static Map<String, BaseSchema> getValidationMap() {
        return validationMap;
    }

    private static Map<String, BaseSchema> validationMap = new HashMap<>();

    public MapSchema() {
        super.setSchema();
    }

    public MapSchema required() {

        addChecks("requiredMap", it -> it instanceof Map<?, ?>);
      //  addChecks(this, it -> it instanceof Map<?, ?>);
      //  addChecks(it -> it instanceof Map<?, ?>, this);

        return this;
    }

    public MapSchema sizeof(int sizeof) {
        // this.size = sizeof;
        addChecks("sizeof", it -> it instanceof Map<?, ?> map && map.size() == sizeof);
       // addChecks(this, it -> it instanceof Map<?, ?> map && map.size() == sizeof);
      //  addChecks(it -> it instanceof Map<?, ?> map && map.size() == sizeof, this);

        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> validationMapTo) {
        validationMap = validationMapTo;

        return this;
    }

}
