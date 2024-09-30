package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import java.io.PrintWriter;
import java.util.Arrays;
import lombok.Getter;

@Getter
public class GuessingResult {
    private static final PrintWriter OUTPUT = new PrintWriter(System.out);

    private final String word;
    private final String hint;
    private final char[] response;

    private final int attemptsNum;
    private int madeAttemptsNum;

    private String message;

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
        StringBuilder responseString = new StringBuilder();
        for (char symbol : response) {
            responseString.append(symbol);
        }

        message = responseString.toString();
        OUTPUT.println(message);
        OUTPUT.flush();
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
