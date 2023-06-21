package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {


    @Test
    public void testValidStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual;

        actual = schema.isValid("");
        assertTrue(actual);

        actual = schema.isValid(null);
        assertTrue(actual);

        schema.required().contains("re").minLength(6);


        actual = schema.required().contains("re").minLength(6).isValid("3rerytytytyt");
        assertTrue(actual);

        actual = schema.required().isValid(null);
        assertFalse(actual);

        actual = schema.required().isValid("");
        assertFalse(actual);

        actual = schema.isValid(5);
        assertFalse(actual);

        actual = schema.minLength(6).isValid("3rerytytytyt");
        assertTrue(actual);


        actual = schema.minLength(10).isValid("testing");
        assertFalse(actual);


    }

    @Test
    public void testNumberValidator() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual;

        actual = schema.isValid(5);
        assertTrue(actual);
        actual = schema.isValid(null);
        assertTrue(actual);

        actual = schema.positive().isValid(null);
        assertTrue(actual);

        schema.required();
        actual = schema.isValid(null);
        assertFalse(actual);
        actual = schema.isValid("5");
        assertFalse(actual);

        actual = schema.isValid(-10);
        assertFalse(actual);

        actual = schema.isValid(-0);
        assertFalse(actual);
        actual = schema.isValid(10);
        assertTrue(actual);

        schema.range(5, 10);
        actual = schema.isValid(5);
        assertTrue(actual);
        actual = schema.isValid(10);
        assertTrue(actual);
        actual = schema.isValid(4);
        assertFalse(actual);
        actual = schema.isValid(11);
        assertFalse(actual);

        schema.range(6, 9);
        actual = schema.isValid(5);
        assertFalse(actual);
        actual = schema.isValid(10);
        assertFalse(actual);
    }

    @Test
    public void testValidMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual;

        actual = schema.isValid(null);
        assertTrue(actual);

        schema.required();

        actual = schema.isValid(null);
        assertFalse(actual);

        actual = schema.isValid(new HashMap<>());
        assertTrue(actual);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        actual = schema.isValid(data);
        assertTrue(actual);

        schema.sizeof(2);
        data.put("key2", "value2");
        actual = schema.isValid(data);
        assertTrue(actual);

        schema.sizeof(1);
        actual = schema.isValid(data);
        assertFalse(actual);

    }


    @Test
    public void testValidMapShapeSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        boolean actual;


        schemas.put("name", v.string().minLength(4));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);

        actual = schema.isValid(human1);
        assertTrue(actual);

        Map<Object, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        actual = schema.isValid(human2);
        assertTrue(actual);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        actual = schema.isValid(human3);
        assertFalse(actual);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        actual = schema.isValid(human4);
        assertFalse(actual);

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "xxx");
        human5.put("age", 5);
        actual = schema.isValid(human5);
        assertFalse(actual);

    }

}
