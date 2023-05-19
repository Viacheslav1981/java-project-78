package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.HashMap;
import java.util.Map;

public class BaseSchema {

    public static void main(String[] args) {
        System.out.println("test");

        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

       // System.out.println(schemas);
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        schema.isValid(human1);

    }


    public boolean isValid(Object data) {
        return !(data == null);
    }

}
