package bowling_game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BowlingGameTest {

    @Test
    @DisplayName("should score 0 for 0 pins dropped in each roll")
    void shouldReturn0ScoreFor0PinsInEachRoll() {
        //given
        var rolls = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var expected = 0;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should score 20 for 1 pin dropped in each roll")
    void shouldReturn20ScoreFor1PinInEachRoll() {
        //given
        var rolls = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        var expected = 20;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should score 300 for perfect game")
    void shouldReturnPerfectGameScore() {
        //given
        var rolls = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        var expected = 300;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should score one spare followed by 3 then 0")
    void shouldScoreOneSpareFollowedBy3Then0() {
        //given
        var rolls = new int[]{5, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var expected = 16;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should score one strike followed by 3 and 4 then 0")
    void shouldScoreOneStrikeFollowedBy3And4Then0() {
        //given
        var rolls = new int[]{10, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var expected = 24;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should score spare by dropping 10 pins in second throw followed by 2 and 2")
    void shouldScoreSpareByDropping10PinsInSecondThrowFollowedBy2And2() {
        //given
        var rolls = new int[]{0, 10, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var expected = 16;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("should not score spare by dropping 10 pins in consecutive two throws in different frames")
    void shouldNotScoreSpareByDropping10PinsInConsecutiveTwoThrowsInDifferentFrames() {
        //given
        var rolls = new int[]{1, 5, 5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var expected = 13;
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

    public Stream<Arguments> validRandomGames(){
        return Stream.of(
                Arguments.of(new int[]{1, 5, 5, 2, 1, 1, 6, 3, 0, 1, 0, 0, 9, 0, 3, 2, 4, 5, 0, 7}, 55),
                Arguments.of(new int[]{1, 5, 5, 5, 1, 1, 6, 4, 0, 1, 0, 10, 9, 0, 3, 2, 4, 5, 0, 10, 4}, 86),
                Arguments.of(new int[]{10, 9, 0, 5, 1, 1, 6, 4, 1, 10, 5, 5, 0, 3, 2, 4, 10, 4, 10}, 109)
        );
    }

    @ParameterizedTest
    @MethodSource("validRandomGames")
    @DisplayName("should calculate score for random games")
    void shouldCalculateScoreForRandomGames(int[] rolls, int expected) {
        //given
        //when
        var result = BowlingGame.getFinalScore(rolls);
        //then
        assertThat(result).isEqualTo(expected);
    }

}