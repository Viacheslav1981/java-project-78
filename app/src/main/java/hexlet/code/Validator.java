package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {

    public static void main(String[] args) {
       // System.out.println("test");
        Validator v = new Validator();
       // StringSchema schema = v.string();
        NumberSchema schema = v.number();

       // schema.isValid2(""); // true
    //    schema.isValid2(null); // true

        schema.required();

     //   schema.isValid2(null); // false


        /*
        schema.isValid2(null); // false
        schema.isValid2(""); // false
        schema.isValid2(5); // false
        schema.isValid2("what does the fox say"); // true
        schema.isValid2("hexlet"); // true

        schema.contains("wh").isValid2("what does the fox say"); // true
        schema.contains("what").isValid2("what does the fox say"); // true
        schema.contains("whatthe").isValid2("what does the fox say"); // false

        schema.isValid2("what does the fox say"); // false

         */



     //   Validator v2 = new Validator();

     //   NumberSchema schema2 = v2.number();

// Пока не вызван метод required(), null считается валидным
     //   schema2.isValid2(null); // true
       // schema2.positive().isValid(null);


       // schema.contains("tes").isValid2("testing");
    }

    public StringSchema string() {
      //  BaseSchema.allChecks.clear();
        return new StringSchema();
    }

    public NumberSchema number() {
     //   BaseSchema.allChecks.clear();
        return new NumberSchema();
    }

    public MapSchema map() {
        BaseSchema.allChecks.clear();
        return new MapSchema();
    }
}
