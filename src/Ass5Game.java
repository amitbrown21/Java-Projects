import GameSettings.Game;

/**
 * this class initializes and runs the game in the default settings.
 */
public class Ass5Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}