package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseSchema {

    public static final List<Object> allChecks = new ArrayList<>();


    public final boolean isNotNull(Object data) {
        return data == null;
    }

  //  public abstract boolean isValid(Object data);

    public final boolean isValid(Object data) {

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

        for (Object allCheck : allChecks) {
            //System.out.println(allChecks.get(i));
            Check check = (Check) allCheck;
            System.out.println(check);
        }

        for (int i = 0; i < allChecks.size(); i++) {
            Check check = (Check) allChecks.get(i);

            Object o = allChecks.get(i).getClass();

            isValidOk = check.check(data);
            if (!check.check(data)) {
              //  allChecks.clear();
                return false;
            }
        }

          // allChecks.clear();
        return true;

    }

    public static void addCheck(Check check) {
        allChecks.add(check);

    }

}
