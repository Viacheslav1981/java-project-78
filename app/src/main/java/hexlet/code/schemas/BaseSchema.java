package hexlet.code.schemas;


import java.util.*;

public abstract class BaseSchema {

    public String schema;
    // public Object schema;

    public static final List<Object> allChecks = new ArrayList<>();
    public static final Map<String, Check> checks = new HashMap<>();


    public BaseSchema(Object schema) {
        this.schema = (String) schema;
    }

    public boolean checking(Object data, String nameCheck) {
        if (checks.containsKey(nameCheck)) {
            Check check = checks.get(nameCheck);
            return check.check(data);
        }
        return true;
    }

    public boolean isValidOfSchema(BaseSchema schema, Object value) {

        if (schema instanceof StringSchema) {
            if ((!checking(value, "requiredString")
                    || !checking(value, "minLength")
                    || !checking(value, "contains"))) {
                return false;
            }

        }
        if (schema instanceof NumberSchema) {
            if ((!checking(value, "requiredNumber")
                    || !checking(value, "positive")
                    || !checking(value, "range"))) {
                return false;
            }
        }
        if (schema instanceof MapSchema) {
            return checking(value, "requiredMap")
                    && checking(value, "sizeof");
        }

        return true;
    }

    public final boolean isValid(Object data) {

        Map<String, BaseSchema> shapeMap = MapSchema.validationMap;
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

    public final boolean isValidd(Object data) {

        boolean isValidOk = true;

        Map<String, BaseSchema> shapeMap = MapSchema.validationMap;
        int sizeOfShapeMap = shapeMap.size();

        int j = 0;

        if (data instanceof Map<?, ?> && sizeOfShapeMap > 0) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) data).entrySet()) {

                Object key = entry.getKey();
                Object value = entry.getValue();


                if (shapeMap.containsKey(key)) {
                    BaseSchema shape = shapeMap.get(key);
                    Check check = (Check) allChecks.get(j);

                    if (!check.check(value)) {
                        // allChecks.clear();
                        return false;
                    }
                }

                j++;

            }

            return true;

        }

        //список всех проверок... из массива?

        // allChecks.stream().filter(data.getClass())


        for (Object allCheck : allChecks) {
            Check check = (Check) allCheck;

            // Object o = allChecks.get(i).getClass();

            isValidOk = check.check(data);
            if (!check.check(data)) {
                //  allChecks.clear();
                return false;
            }
        }

        // allChecks.clear();
        return true;

    }

    public static void addChecks(String schema, Check check) {
        checks.put(schema, check);

    }

    public static void addCheck(Check check) {
        allChecks.add(check);

    }

}
