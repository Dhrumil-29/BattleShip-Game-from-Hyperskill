package battleship;

public class Main {
    public static void main(String[] args) {
        // Write your code here
        /*
         * 10 x 10
         * col 1 to 10
         * row a to j
         *----------------------------------------------------------------------------------
         * ~ fog of war : unknown area on the opponent's field
         * O cell with your ship
         * x the ship was hit
         * M miss the shot
         * ---------------------------------------------------------------------------------
         * 5 ship : aircraft carrier(5 cell), battleship(4 cell),
         *        submarine(3 cell), cruiser(3 cell), destroyer
         * ---------------------------------------------------------------------------------
         * => To place a ship, enter two coordinates: the beginning and the end of the ship.
         * =>If an error occurs in the input coordinates, your program should report it.
         *       The message should contain the word Error.
         * ---------------------------------------------------------------------------------
         * */

        BattleShipGamePlay game = new BattleShipGamePlay();

        game.startGame();


    }

}
