import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collection;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameOfLifeTest {
    @Mock
    GameOfLifeState initialstate;
    @Mock
    GameOfLifeState neighborstate;
    @Mock
    GameOfLifeState newState;
    @Mock
    GameOfLifeTest colc;
    @Mock
    GameOfLiveCell golc;
    @Mock
    GameOfLiveCell neighbor;

    @BeforeEach
    private void setup() {
        golc = new GameOfLiveCell(initialstate);
        for(int i = 0; i<8;i++) {
            golc.addNeighbor(neighbor);
        }
    }

    @Test
    void returnsInitialStateWithoutOtherInteractions() {
        golc = new GameOfLiveCell(initialstate);
        GameOfLifeState currentState = golc.getState();
        assertThat(currentState)
                .describedAs("intial State")
                .isEqualTo(initialstate);


    }
    @Test
    void passesNeighborsStateToOwnWhenCalcFutures() {
        golc.calculateFuture();

        Collection<GameOfLifeState> neighborState = Arrays.asList(
                neighborstate,
                neighborstate,
                neighborstate,
                neighborstate,
                neighborstate,
                neighborstate,
                neighborstate,
                neighborstate
        );
        verify(initialstate)
                .calculateNext(neighborState);
    }
    @Test
    void doesNotChangesStateWhenCalculatingNext() throws Exception {
        golc.calculateFuture();

        GameOfLifeState currentState = golc.getState();

        assertThat(currentState)
                .describedAs("initial State")
                .isEqualTo(initialstate);
    }
    @Test
    void changeStateOnRequest() throws Exception {
        when(initialstate.calculateNext(anyList()))
                .thenReturn(newState);
        golc.calculateFuture();
        golc.updateState();

        GameOfLifeState currentState = golc.getState();
        assertThat(currentState)
                .describedAs("state after change request")
                .isEqualTo(newState);
    }
}
