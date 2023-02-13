package battleship;

import java.util.Scanner;

public class BattleShipGamePlay {

    private static Player player1;
    private static Player player2;

    private void printGameField(char[][] opponentField, char[][] playerField) {
        char row = 'A';
        System.out.print("  ");

        for (int i = 0; i < 10; i++) System.out.printf("%d ", i + 1);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.printf("%c ", row++);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%c ", opponentField[i][j]);
            }
            System.out.println();
        }

        System.out.println("---------------------");

        row = 'A';
        System.out.print("  ");

        for (int i = 0; i < 10; i++) System.out.printf("%d ", i + 1);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.printf("%c ", row++);
            for (int j = 0; j < 10; j++) {
                if (playerField[i][j] == 'A' || playerField[i][j] == 'B' || playerField[i][j] == 'S' || playerField[i][j] == 'D' || playerField[i][j] == 'C')
                    System.out.print("O ");
                else
                    System.out.printf("%c ", playerField[i][j]);
            }
            System.out.println();
        }

    }

    private boolean validInput(String input) {
        if (input.length() < 2 || input.length() > 3 || !input.matches("[A-J][1-9]0{0,1}") || (input.length() == 3 && input.charAt(1) != '1')) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }
        return true;
    }

    private boolean checkHitOrMiss(int x, int y, char[][] playerField) {

        if (playerField[x][y] != '~') {
            return true;
        }

        playerField[x][y] = 'M';
        return false;
    }

    private void takeShotOn(Player player) {

        String input = null;
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();

        if (validInput(input)) {

            int x = input.charAt(0) - 'A';
            int y = Integer.parseInt(input.substring(1)) - 1;

            if (checkHitOrMiss(x, y, player.playerField)) {
                switch (player.playerField[x][y]) {
                    case 'A': {
                        player.aircraft--;
                        if (player.aircraft == 0) {
                            if (player.isDestroyed()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    }
                    case 'B': {
                        player.battleship--;
                        if (player.battleship == 0) {
                            if (player.isDestroyed()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    }
                    case 'S': {
                        player.submarine--;
                        if (player.submarine == 0) {
                            if (player.isDestroyed()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    }
                    case 'C': {
                        player.cruiser--;
                        if (player.cruiser == 0) {
                            if (player.isDestroyed()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    }
                    case 'D': {
                        player.destroyer--;
                        if (player.destroyer == 0) {
                            if (player.isDestroyed()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        break;
                    }
                }
                player.playerField[x][y] = 'X';
            } else {
                System.out.println("You missed! Try again:");
            }
        }
    }

    private void play() {
        boolean chance = false;
        //false means player1 chance
        //true means player2 chance
        boolean gameOver = false;

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();

            if (!chance) {
                //player 1
                printGameField(player2.gameField, player1.playerField);
                System.out.println("Player 1,it's your turn:");

                takeShotOn(player2);

                if (player2.isDestroyed()) {
                    gameOver = true;
                }

            } else {
                //player 2
                printGameField(player1.gameField, player2.playerField);
                System.out.println("Player 2,it's your turn:");

                takeShotOn(player1);

                if (player1.isDestroyed()) {
                    gameOver = true;
                }
            }

            chance = !chance;
        }

    }

    protected void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1, place your ships on the game field");
        player1 = new Player("Player 1");
        player1.arrangeShips();

        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        System.out.println("Player 2, place your ships on the game field");
        player2 = new Player("Player 2");
        player2.arrangeShips();

        play();
    }
}
