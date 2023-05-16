package hexlet.code;

import hexlet.code.shemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testValidStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual;

        actual = schema.required().isValid("");
        assertFalse(actual);

        actual = schema.minLength(6).isValid("test12");
        assertTrue(actual);

        actual = schema.contains("test").isValid("testing");
        assertTrue(actual);


       // schema.minLength(6).contains("we");
      //  schema.contains("tes");

        //  System.out.println(schema.isValid(""));
       //  System.out.println(schema.isValid(null));

      //  System.out.println(schema.isValid("testrrrr"));

    }
}
