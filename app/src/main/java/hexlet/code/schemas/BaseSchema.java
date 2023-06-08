package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseSchema {
    public static final Map<String, Check> CHECKS = new HashMap<>();

    public BaseSchema() {
    }

    public void setSchema() {

    }

    private static boolean checking(Object data, String nameCheck) {
        if (CHECKS.containsKey(nameCheck)) {
            Check check = CHECKS.get(nameCheck);
            return check.check(data);
        }
        return true;
    }


    private static boolean isValidOfSchema(BaseSchema kindOfSchema, Object value) {

        if (kindOfSchema instanceof StringSchema) {

            return (checking(value, "requiredString")
                    && checking(value, "minLength")
                    && checking(value, "contains"));

        }

        if (kindOfSchema instanceof NumberSchema) {

            return (checking(value, "requiredNumber")
                    && checking(value, "positive")
                    && checking(value, "range"));
        }
        if (kindOfSchema instanceof MapSchema) {

            return checking(value, "requiredMap")
                    && checking(value, "sizeof");
        }

        return true;
    }

    public final boolean isValid(Object data) {

        Map<String, BaseSchema> shapeMap = MapSchema.getValidationMap();
        int sizeOfShapeMap = shapeMap.size();

        boolean retOfValid = true;

        if (data instanceof Map<?, ?> && sizeOfShapeMap > 0) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) data).entrySet()) {

                Object key = entry.getKey();
                Object value = entry.getValue();

                if (shapeMap.containsKey(key)) {
                    BaseSchema shape = shapeMap.get(key);
                    retOfValid = isValidOfSchema(shape, value);

                    if (!retOfValid) {
                        return false;
                    }

                }

            }

        } else {
            retOfValid = isValidOfSchema(this, data);
        }
        return retOfValid;
    }

    public static void addChecks(String kindOfSchema, Check check) {
        CHECKS.put(kindOfSchema, check);

    }

}
