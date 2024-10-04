package backend.academy.gallows.guessing.result;

import backend.academy.gallows.dictionary.WordWithHint;
import java.io.PrintWriter;
import java.util.Arrays;
import lombok.Getter;

@Getter
public class GuessingResult {
    private final String word;
    private final String hint;
    private final char[] response;

    private final int attemptsNum;
    private int madeAttemptsNum;

    private final PrintWriter output;

    public GuessingResult(WordWithHint wordWithHint, int attemptsNum, PrintWriter output) {
        this.word = wordWithHint.word();
        this.hint = wordWithHint.hint();

        response = new char[word.length()];
        Arrays.fill(response, '_');

        this.attemptsNum = attemptsNum;
        madeAttemptsNum = 0;

        this.output = output;
    }

    public String getResponse() {
        return new String(response);
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
        String message = getResponse();
        output.println(message);
        output.flush();
    }

    public boolean isGameWin() {
        return !new String(response).contains("_");
    }

    public boolean isGameLoss() {
        return madeAttemptsNum >= attemptsNum;
    }

    public boolean isGameOver() {
        return isGameWin() || isGameLoss();
    }
}
