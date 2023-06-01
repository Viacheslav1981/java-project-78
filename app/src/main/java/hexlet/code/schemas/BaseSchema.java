package hexlet.code.schemas;


import java.util.*;

public abstract class BaseSchema {

    public String schema;

    public static final List<Object> allChecks = new ArrayList<>();
    public static final Map<String, Check> checks = new HashMap<>();

    public final boolean isNotNull(Object data) {
        return data == null;
    }

    public BaseSchema(String schema) {
        this.schema = schema;

    }

    public boolean checking(Object data, String nameCheck) {
        if (checks.containsKey(nameCheck)) {
            Check check = checks.get(nameCheck);
            return check.check(data);
        }
        return true;
    }

    // public final boolean isValid(Object object) {return checks.values().stream().allMatch(check -> check.test(object));}

    public final boolean isValid(Object data) {


        Map<String, BaseSchema> shapeMap = MapSchema.validationMap;
        int sizeOfShapeMap = shapeMap.size();

        int j = 0;

        if (data instanceof Map<?, ?> && sizeOfShapeMap > 0) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) data).entrySet()) {

                Object key = entry.getKey();
                Object value = entry.getValue();


                if (shapeMap.containsKey(key)) {
                    BaseSchema shape = shapeMap.get(key);
                    if (shape instanceof StringSchema) {
                        if ((!checking(value, "requiredString")
                                || !checking(value, "minLength")
                                || !checking(value, "contains"))) {
                            return false;
                        }

                    }
                    if (shape instanceof NumberSchema) {
                        if ((!checking(value, "requiredNumber")
                                || !checking(value, "positive")
                                || !checking(value, "range"))) {
                            return false;
                        }
                    }
                    if (shape instanceof MapSchema) {
                        if ((!checking(value, "requiredMap")
                                || !checking(value, "sizeof")
                        )) {
                            return false;
                        }
                    }


                }
            }

             //   return true;

            }

            // System.out.println(schema);
          //  boolean ret = true;

            if (schema.equals("StringSchema")) {
                if ((!checking(data, "requiredString")
                        || !checking(data, "minLength")
                        || !checking(data, "contains"))) {
                    return false;
                }
            }
            if (schema.equals("NumberSchema")) {
                if ((!checking(data, "requiredNumber")
                        || !checking(data, "positive")
                        || !checking(data, "range"))) {
                    return false;
                }
            }

            if (schema.equals("MapSchema")) {
                if ((!checking(data, "requiredMap")
                        || !checking(data, "sizeof")
                )) {
                    return false;
                }
            }

        return true;
    }

    //  public abstract boolean isValid(Object data);

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


        for (int i = 0; i < allChecks.size(); i++) {
            Check check = (Check) allChecks.get(i);

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
