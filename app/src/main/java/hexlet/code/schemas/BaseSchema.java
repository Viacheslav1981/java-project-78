package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final LinkedList<Predicate<Object>> checks = new LinkedList<>();

    public abstract BaseSchema required();

    protected final void addChecks(Predicate<Object> check) {
        checks.add(check);
    }


    public final boolean isValid(Object data) {

        for (Predicate<Object> check : checks) {
            if (!check.test(data)) {
                return false;
            }
        }
        return true;
    }

}
