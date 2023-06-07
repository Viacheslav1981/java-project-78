package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean retValid = true;

      //  switch (schema.toString()) {

   //     }

        if (schema instanceof StringSchema) {
            retValid = (checking(value, "requiredString")
                    && checking(value, "minLength")
                    && checking(value, "contains"));

         //   return (checking(value, "requiredString")
         //           && checking(value, "minLength")
         //           && checking(value, "contains"));

        }
        if (schema instanceof NumberSchema) {
            retValid = (checking(value, "requiredNumber")
                    && checking(value, "positive")
                    && checking(value, "range"));

         //   return (checking(value, "requiredNumber")
         //           && checking(value, "positive")
        //            && checking(value, "range"));
        }
        if (schema instanceof MapSchema) {
            retValid = checking(value, "requiredMap")
                    && checking(value, "sizeof");

            //return checking(value, "requiredMap")
              //      && checking(value, "sizeof");
        }

        //return true;
        return retValid;
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

    public static void addChecks(String schema, Check check) {
        checks.put(schema, check);

    }

    public static void addCheck(Check check) {
        allChecks.add(check);

    }

}
