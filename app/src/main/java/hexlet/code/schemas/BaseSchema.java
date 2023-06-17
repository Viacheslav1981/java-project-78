package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    public static final Map<String, Check> CHECKS = new HashMap<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object data) {

        boolean retOfValid = true;

        Map<String, BaseSchema> shapeMap = MapSchema.getValidationMap();
        int sizeOfShapeMap = shapeMap.size();

        if (data instanceof Map<?, ?> && sizeOfShapeMap > 0) {
            for (Map.Entry<?, ?> enter : ((Map<?, ?>) data).entrySet()) {

                Object nameForCheck = enter.getKey();
                Object valueForCheck = enter.getValue();

                if (shapeMap.containsKey(nameForCheck)) {

                    if (!(shapeMap.get(nameForCheck).checking(valueForCheck))) {
                        return false;
                    }

                }

            }
        } else {
            retOfValid = checking(data);
        }

        return retOfValid;
    }

    public final boolean checking(Object data) {

        for (Map.Entry<String, Check> entry : CHECKS.entrySet()) {

            String base = this.getClass().getName().substring(20);
            Check check = entry.getValue();
            String str = check.getClass().getName();

            if ((str.contains(base)) && !(check.check(data))) {
                return false;
            }
        }
        return true;
    }

    public static void addChecks(String nameCheck, Check check) {
        CHECKS.put(nameCheck, check);

    }

    protected void setSchema() {
    }
}
