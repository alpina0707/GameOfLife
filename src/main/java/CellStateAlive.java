import java.util.Collection;
import java.util.Collections;

final class CellStateAlive implements Cellstate {
    @Override
    public Cellstate calculateFuture(Collection<Cellstate> neighborState) {
        int countOfAlive = Collections.frequency(neighborState, this);
        //boolean isChangedState = (3== neighborState.size()-countOfDead);
        return countOfAlive < 2 || countOfAlive > 3 ? new CellStateDead(): new CellStateAlive();
    }
    @Override
    public boolean equals(Object other) {
        return other instanceof CellStateAlive;
    }
    @Override
    public int hashCode() {
        return 1;
    }
}
