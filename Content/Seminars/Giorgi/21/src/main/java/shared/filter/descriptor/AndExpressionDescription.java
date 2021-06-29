package shared.filter.descriptor;

import java.util.ArrayList;
import java.util.List;

public class AndExpressionDescription implements ExpressionDescription {
    private final List<ExpressionDescription<Boolean>> children;

    public AndExpressionDescription() {
        children = new ArrayList<>();
    }

    public AndExpressionDescription(List<ExpressionDescription<Boolean>> children) {
        this.children = children;
    }

    public AndExpressionDescription add(ExpressionDescription<Boolean> ed) {
        children.add(ed);
        return this;
    }

    @Override
    public List<ExpressionDescription<Boolean>> getChildren() {
        return children;
    }
}
