package my.project.step3.domain;

import my.project.common.domain.Rewards;
import my.project.common.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * Created : 2020-12-10 오전 8:17
 * Developer : Seo
 */
class RewardsTest {

    @DisplayName("잘못된 입력 값")
    @ParameterizedTest
    @ValueSource(strings = {"Java", "Scala", "Groov", "Pytho", "Go", "Swift"})
    void givenInvalid_thenThrow(String input) {
        Users users = new Users(input);
        String rewards = "100,꽝";

        assertThatThrownBy(() -> new Rewards(users, rewards))
                .withFailMessage("실행 결과를 확인해주십시요.(참여자 : %s, 실행 결과 : %s)", 6, 2)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("생성")
    @Test
    void whenValid() {
        Users users = new Users("a,b");
        String rewards = "100,꽝";
        assertThat(new Rewards(users, rewards).getRewards()).hasSize(2);
    }

    @DisplayName("결과리스트를 포인터배열 인덱스로 조회")
    @Test
    void givenArrayIndex_shouldGetResult() {
        Users users = new Users("a,b");
        String rewards = "100,꽝";
        assertThat(new Rewards(users, rewards).getReward(0).get()).isEqualTo("100  ");
    }
}