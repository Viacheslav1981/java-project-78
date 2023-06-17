package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema {
    public static final Map<String, Check> CHECKS = new HashMap<>();

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

        boolean retOfValid;
        Check check;

        for (Map.Entry<String, Check> entry : CHECKS.entrySet()) {

            String base = this.getClass().getName().substring(20);
            check = entry.getValue();
            String str = check.getClass().getName();

            if (str.contains(base)) {
                retOfValid = check.check(data);

                if (!(retOfValid)) {
                    return false;
                }
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
