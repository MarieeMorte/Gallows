package backend.academy.gallows.game.session;

import backend.academy.gallows.dictionary.Difficulties;
import backend.academy.gallows.dictionary.Themes;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class GameSessionTest {
    @Test
    void testChoosingWithValidInput() {
        // Arrange
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        int choice = GameSession.choosing();

        // Assert
        assertThat(choice).isEqualTo(2);
    }

    @Test
    void testChoosingWithInvalidInput() {
        // Arrange
        String input = "5\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        int choice = GameSession.choosing();

        // Assert
        assertThat(choice).isEqualTo(3);
    }

    @Test
    void testSetDifficulty() {
        // Act
        GameSession.setDifficulty(1);
        // Assert
        assertThat(GameSession.difficulty()).isEqualTo(Difficulties.EASY);

        // Act
        GameSession.setDifficulty(2);
        //Assert
        assertThat(GameSession.difficulty()).isEqualTo(Difficulties.MEDIUM);

        // Act
        GameSession.setDifficulty(3);
        // Assert
        assertThat(GameSession.difficulty()).isEqualTo(Difficulties.HARD);
    }

    @Test
    void testThemeChoosing() {
        // Arrange
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        GameSession.themeChoosing();

        // Assert
        assertThat(GameSession.theme()).isEqualTo(Themes.FRUITS);
    }

    @Test
    void testSetTheme() {
        // Act
        GameSession.setTheme(1);
        // Assert
        assertThat(GameSession.theme()).isEqualTo(Themes.FRUITS);

        // Act
        GameSession.setTheme(2);
        // Assert
        assertThat(GameSession.theme()).isEqualTo(Themes.BERRIES);

        // Act
        GameSession.setTheme(3);
        // Assert
        assertThat(GameSession.theme()).isEqualTo(Themes.VEGETABLES);
    }

    @Test
    void testAttemptNumChoosing() {
        // Arrange
        String input = "6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        GameSession.attemptNumChoosing();

        // Assert
        assertThat(GameSession.attemptsNum()).isEqualTo(6);
    }

    @Test
    void testGetUserInputWithValidInput() {
        // Arrange
        String input = "а\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        char userInput = GameSession.getUserInput();

        // Assert
        assertThat(userInput).isEqualTo('а');
    }

    @Test
    void testGetUserInputWithInvalidInput() {
        // Arrange
        String input = "к\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        char userInput = GameSession.getUserInput();

        // Assert
        assertThat(userInput).isEqualTo('к');
    }

    @Test
    void testIsCyrillicLetter() {
        // Act, Assert
        assertThat(GameSession.isCyrillicLetter('а')).isTrue();
        assertThat(GameSession.isCyrillicLetter('А')).isTrue();
        assertThat(GameSession.isCyrillicLetter('ё')).isTrue();
        assertThat(GameSession.isCyrillicLetter('Ё')).isTrue();
        assertThat(GameSession.isCyrillicLetter('b')).isFalse();
        assertThat(GameSession.isCyrillicLetter('1')).isFalse();
    }
}
