import java.util.Collection;

public interface GameOfLifeState {
    public GameOfLifeState calculateNext(Collection<GameOfLifeState> neighborStates);

}
