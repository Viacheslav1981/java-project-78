package hexlet.code.shemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkSize = false;
    private boolean checkShape = false;
    private Map<String, BaseSchema> validationMap = new HashMap<>();

    private int size;

    public MapSchema required() {
        this.checkRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.checkSize = true;
        this.size = size;
        return this;
    }

    public MapSchema shape(Map validationMap) {
        this.checkShape = true;
        this.validationMap = validationMap;

        return this;
    }

    public boolean isValid(Object data) {
        boolean bRet = true;

        if (this.checkRequired) {
           return (data instanceof Map);
        }

        if (this.checkSize) {
            Map map = (Map) data;
            int size = map.size();
            bRet = size == this.size;

        }

        if (this.checkShape) {
            Map map = (Map) data;

            for (Object o : map.entrySet()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) o;
                String key = entry.getKey();
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
