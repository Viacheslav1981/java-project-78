package hexlet.code.shemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkSize = false;

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

    public boolean isValid(Object data) {
        boolean bRet = true;

        if (this.checkRequired) {
            bRet = (data instanceof Map);
        }

        if (!bRet) {
            return false;
        }

        if (this.checkSize) {
            Map map = (Map) data;
            int size = map.size();
            bRet = size == this.size;

        }

        return bRet;
    }


}
