package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        addChecks(it -> it instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        addChecks(it -> it instanceof Map<?, ?> map && map.size() == sizeof);
        return this;
    }


    public MapSchema shape(Map<String, BaseSchema> schemas) {

        for (Map.Entry<String, BaseSchema> enter : schemas.entrySet()) {
            String key = enter.getKey();
            addChecks(o -> schemas.get(key).isValid(((Map<?, ?>) o).get(key)));
        }

        return this;
    }

}
