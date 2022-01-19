import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CellStateDeadAndAliveTest {

    private static final CellStateDead CELL_STATE_DEAD = new CellStateDead();
    private static final CellStateAlive CELL_STATE_ALIVE = new CellStateAlive();
    static int NEIGHBOR_COUNT = 8;


    static Cellstate alifeNeighbor = new Cellstate() {
        @Override
        public Cellstate calculateFuture(Collection<Cellstate> neighborState) {
            return null;
        }
    };

    @BeforeEach
    void setup() throws Exception {

    }
    @ParameterizedTest
    @MethodSource("testCasesForStateDead")
    void test(String testName, List<Cellstate> neighborState, Cellstate expectedResult) {
        Cellstate calculatedState = CELL_STATE_DEAD.calculateFuture(neighborState);
        assertThat(calculatedState).describedAs(testName).isEqualTo(expectedResult);
    }
    @ParameterizedTest
    @MethodSource("testCasesForStateAlive")
    void test2(String testName, List<Cellstate> neighborState, Cellstate expectedResult) {
        Cellstate calculatedState2 = CELL_STATE_ALIVE.calculateFuture(neighborState);
        assertThat(calculatedState2).describedAs(testName).isEqualTo(expectedResult);
    }
    private static Stream<Arguments> testCasesForStateDead() {
        //IntStream.range(0,8).mapToObj(i-> CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
        return Stream.of(
                Arguments.of("with 0 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
        Arguments.of("with 2 alive neighbors",
                IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 2 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
                Arguments.of("with 4 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 4 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
                Arguments.of("with 3 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 3 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_ALIVE));

                /*
                "with zero alive neighbors",
                Arrays.array(CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD,
                CELL_STATE_DEAD),
                        CELL_STATE_DEAD),
                Arguments.of(
                        "with 3 alive neighbors",
                        Arrays.asList(
                                CELL_STATE_ALIVE
                        ), CELL_STATE_DEAD, CELL_STATE_ALIVE, CELL_STATE_DEAD, CELL_STATE_ALIVE, CELL_STATE_DEAD, CELL_STATE_DEAD, CELL_STATE_DEAD,
                        CELL_STATE_ALIVE)
                        );

                 */
    }
    private static Stream<Arguments> testCasesForStateAlive() {
        //IntStream.range(0,8).mapToObj(i-> CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
        return Stream.of(
        Arguments.of("with 0 alive neighbors",
                IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
                Arguments.of("with 2 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 2 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_ALIVE),
                Arguments.of("with 3 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 3 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_ALIVE),
                Arguments.of("with 4 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 4 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD),
                Arguments.of("with 8 alive neighbors",
                        IntStream.range(0,NEIGHBOR_COUNT).mapToObj(i->  i < 8 ? CELL_STATE_ALIVE : CELL_STATE_DEAD).collect(Collectors.toList()),CELL_STATE_DEAD));
    }
}