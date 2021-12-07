package nextstep.ladder.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {

    @DisplayName("양쪽 다 길이 있다면 예외를 던진다.")
    @Test
    void create_bothWay_throwsException() {
        assertThatThrownBy(() -> new Direction(true, true))
                .isInstanceOf(BothWayException.class);
    }

    @Test
    void equals() {
        assertThat(new Direction(true, false)).isEqualTo(new Direction(true, false));
    }

    @DisplayName("첫 번째 Direction의 left는 false 이다.")
    @ParameterizedTest(name = "[{index}] right: {0}")
    @ValueSource(booleans = {true, false})
    void first(boolean right) {
        assertThat(Direction.first(right)).isEqualTo(new Direction(false, right));
    }

    @DisplayName("마지막 Direction의 right는 false 이다.")
    @ParameterizedTest(name = "[{index}] left: {0}")
    @ValueSource(booleans = {true, false})
    void last(boolean left) {
        Direction direction = Direction.first(left);
        assertThat(direction.last()).isEqualTo(new Direction(left, false));
    }

    @DisplayName("current.right = next.left")
    @ParameterizedTest(name = "[{index}] currentRight: {0}, nextLeft: {1}")
    @CsvSource({
            "true, true",
            "false, false",
    })
    void next(boolean currentRight, boolean nextLeft) {
        Direction current = new Direction(false, currentRight);
        assertThat(current.next(() -> false)).isEqualTo(new Direction(nextLeft, false));
    }

}