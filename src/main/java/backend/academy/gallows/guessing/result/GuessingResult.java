package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import lombok.Getter;
import java.util.Arrays;

public class GuessingResult {
    @Getter private String word;
    @Getter private String hint;
    @Getter private char[] response;
    private final int attemptsNum;
    @Getter private int madeAttemptsNum;

    public GuessingResult(WordWithHint wordWithHint, int attemptsNum) {
        this.word = wordWithHint.word();
        this.hint = wordWithHint.hint();

        response = new char[word.length()];
        Arrays.fill(response, '_');

        this.attemptsNum = attemptsNum;
        madeAttemptsNum = 0;
    }

    public void updateResponse(char letter) {
        if (word.indexOf(letter) >= 0) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    response[i] = letter;
                }
            }
        } else {
            madeAttemptsNum++;
        }
    }

    public void displayResponse() {
        for (char symbol : response) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    public boolean isGameWin() {
        for (char symbol : response) {
            if (symbol == '_') {
                return false;
            }
        }
        return true;
    }

    public boolean isGameLoss() {
        return madeAttemptsNum >= attemptsNum;
    }

    public boolean isGameOver() {
        return isGameWin() || isGameLoss();
    }

}
