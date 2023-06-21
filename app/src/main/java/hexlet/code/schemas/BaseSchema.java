package hexlet.code.schemas;

import java.util.*;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final LinkedList<Predicate<Object>> CHECKS = new LinkedList<>();

    public abstract BaseSchema required();

    protected final void addChecks(Predicate<Object> check) {
        CHECKS.add(check);
    }


    public final boolean isValid(Object data) {

        for (Predicate<Object> check : CHECKS) {
            if (!check.test(data)) {
                return false;
            }
        }
        return true;

    }

}
