import java.util.Collection;
import java.util.Collections;

final class CellStateDead implements Cellstate {
    @Override
    public Cellstate calculateFuture(Collection<Cellstate> neighborState) {
        int countOfDead = Collections.frequency(neighborState, this);
        boolean isChangedState = (3== neighborState.size()-countOfDead);
        return isChangedState? new CellStateAlive(): this;
    }
    //@Override
    //public String toString() {
    //    return "CellStateDead";
    //}
    @Override
    public boolean equals(Object other) {
        return other instanceof CellStateDead;
    }
    @Override
    public int hashCode() {
        return 1;
    }
}
