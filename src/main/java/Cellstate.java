import java.util.Collection;

public interface Cellstate {
    Cellstate calculateFuture(Collection<Cellstate> neighborState);
}
