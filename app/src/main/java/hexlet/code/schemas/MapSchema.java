package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkSize = false;
    private boolean checkShape = false;
    public static Map<String, BaseSchema> validationMap = new HashMap<>();

    private int size;

    public MapSchema required() {
        this.checkRequired = true;

        addCheck(it -> it instanceof Map<?,?>);
        return this;
    }

    public MapSchema sizeof(int sizeof) {
        this.checkSize = true;
        this.size = sizeof;

        addCheck(it -> it instanceof Map<?,?> map && map.size() == size);
        return this;
    }

    public MapSchema shape(Map <String, BaseSchema> validationMapTo) {
        this.checkShape = true;
        validationMap = validationMapTo;



        return this;
    }
    /*

    public boolean isValid(Object data) {
        boolean bRet = true;


        if ((this.checkRequired) && (!(data instanceof Map))) {
            return false;
        }

        if (this.checkSize) {
            Map map = (Map) data;
            int sizeOfMap = map.size();
            bRet = sizeOfMap == this.size;

        }

        if (this.checkShape) {
            Map map = (Map) data;

            for (Object o : map.entrySet()) {
                Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) o;
                Object key = entry.getKey();
                Object value = entry.getValue();

                if (this.validationMap.containsKey(key)) {
                    BaseSchema shape = this.validationMap.get(key);
                    bRet = shape.isValid(value);
                    if (!bRet) {
                        return false;
                    }
                }
            }
        }
        return bRet;
    }

     */


}
