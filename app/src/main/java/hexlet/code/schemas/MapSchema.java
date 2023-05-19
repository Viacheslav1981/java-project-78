package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkSize = false;
    private boolean checkShape = false;
    private Map<Object, BaseSchema> validationMap = new HashMap<>();

    private int size;

    public MapSchema required() {
        this.checkRequired = true;
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        this.checkSize = true;
        this.size = sizeof;
        return this;
    }

    public MapSchema shape(Map validationMap) {
        this.checkShape = true;
        this.validationMap = validationMap;

        return this;
    }

    public boolean isValid(Object data) {
        boolean bRet = true;


        if ((this.checkRequired) && (!(data instanceof Map))) {
            return false;
        }

        if (this.checkSize) {
            Map map = (Map) data;
            int size = map.size();
            bRet = size == this.size;

        }

        if (this.checkShape) {
            Map map = (Map) data;

            for (Object o : map.entrySet()) {
                Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) o;
                Object key = entry.getKey();
                Object value = entry.getValue();

                if (this.validationMap.containsKey(key)) {
                    BaseSchema checkShape = this.validationMap.get(key);
                    bRet = checkShape.isValid(value);
                    if (!bRet) {
                        return false;
                    }
                }
            }
        }
        return bRet;
    }


}
