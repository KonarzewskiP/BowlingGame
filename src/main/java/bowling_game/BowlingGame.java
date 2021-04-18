package bowling_game;

public class BowlingGame {
    private BowlingGame() {
    }

    public static int getFinalScore(int[] rolls) {
        int score = 0;
        int index = 0;

        for (int frame = 1; frame <= 10; frame++) {

            if (isStrike(rolls[index])) {
                score += rolls[index] + rolls[index + 1] + rolls[index + 2];
                index++;
            } else if (isSpare(rolls[index] + rolls[index + 1])) {
                score += 10 + rolls[index + 2];
                index += 2;
            } else {
                score += rolls[index] + rolls[index + 1];
                index += 2;
            }
        }
        return score;
    }

    private static boolean isStrike(int roll) {
        return roll == 10;
    }

    private static boolean isSpare(int roll) {
        return roll == 10;
    }
}
