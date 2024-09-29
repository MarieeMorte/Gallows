package backend.academy.gallows.guessing.result;

import lombok.Getter;
import java.util.Arrays;

public class GuessingResult {
    private String word;
    @Getter private char[] response;
    private int attemptsNum;
    @Getter private int madeAttemptsNum;

    public GuessingResult(String word, int attemptsNum) {
        this.word = word;
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
        return !isGameWin() && !isGameLoss();
    }

}
