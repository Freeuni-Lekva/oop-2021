package shared.filter.descriptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface ExpressionDescription<T> extends Serializable {
    default public List<ExpressionDescription> getChildren() {
        return new ArrayList<>();
    }
}
