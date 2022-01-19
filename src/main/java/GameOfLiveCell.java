import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameOfLiveCell {
    private GameOfLifeState initialState;
    private GameOfLifeState futureState;
    private final Collection<GameOfLiveCell> neighbors = new LinkedList<>();


    public GameOfLiveCell(GameOfLifeState initialState) {
        this.initialState = initialState;
    }
    public GameOfLifeState getState() {
        return this.initialState;
    }
    public void updateState() {
        this.initialState = this.futureState;
    }
    public void addNeighbor(GameOfLiveCell neighbor) {
        this.neighbors.add(neighbor);
    }
    public void calculateFuture() {
        List<GameOfLifeState> neighborStates = this.neighbors.stream()
                .map(GameOfLiveCell::getState)
                .collect(Collectors.toList());
        this.futureState = initialState.calculateNext(neighborStates);
    }
}
