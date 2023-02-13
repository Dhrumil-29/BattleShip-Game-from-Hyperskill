package battleship;

import java.util.Arrays;
import java.util.Scanner;

class Player {

    String playerName;
    char[][] playerField;
    char[][] gameField;

    int aircraft;
    int battleship;
    int submarine;
    int cruiser;
    int destroyer;


    public Player(String name) {
        playerName = name;
        playerField = new char[10][10];
        gameField = new char[10][10];

        for (int i = 0; i < 10; i++) {
            Arrays.fill(gameField[i], '~');
            Arrays.fill(playerField[i], '~');
        }

        aircraft = 5;
        battleship = 4;
        submarine = 3;
        cruiser = 3;
        destroyer = 2;
    }

    protected boolean isDestroyed() {
        if (aircraft == 0 && battleship == 0 && submarine == 0 && cruiser == 0 && destroyer == 0)
            return true;
        return false;
    }

    private void printField(char[][] field) {
        char row = 'A';
        System.out.print("  ");

        for (int i = 0; i < 10; i++) System.out.printf("%d ", i + 1);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.printf("%c ", row++);
            for (int j = 0; j < 10; j++) {
                if (field[i][j] == 'A' || field[i][j] == 'B' || field[i][j] == 'S' || field[i][j] == 'D' || field[i][j] == 'C')
                    System.out.printf("O ");
                else
                    System.out.printf("%c ", field[i][j]);
            }
            System.out.println();
        }
    }

    protected void arrangeShips() {

        printField(playerField);
        arrangeAircraftCarrier('A');
        arrangeBattleship('B');
        arrangeSubmarine('S');
        arrangeCruiser('C');
        arrangeDestroyer('D');
    }

    private boolean checkCoordinates(String cor1, String cor2, int len, char c) {
        if (cor1.length() < 2 || cor2.length() < 2 || cor1.length() > 3 || cor2.length() > 3) {
            System.out.println("Error! Wrong ship location! Ship location is from (A - J), (1 - 10)! Try again:");
            return false;
        }

        String stry1 = cor1.substring(1);
        String stry2 = cor2.substring(1);

        if (!stry1.matches("\\d+")) {
            System.out.println("Error! Wrong ship location! Ship location is from (A - J), (1 - 10)! Try again:");
            return false;
        }
        if (!stry2.matches("\\d+")) {
            System.out.println("Error! Wrong ship location! Ship location is from (A - J), (1 - 10)! Try again:");
            return false;
        }

        if (!checkRow(cor1.charAt(0), cor2.charAt(0)) || !checkColumn(Integer.parseInt(stry1), Integer.parseInt(stry2))) {
            System.out.println("Error! Wrong ship location! Ship location is from (A - J), (1 - 10)! Try again:");
            return false;
        }

        int x1 = cor1.charAt(0) - 'A';
        int x2 = cor2.charAt(0) - 'A';
        int y1 = Integer.parseInt(stry1) - 1;
        int y2 = Integer.parseInt(stry2) - 1;

        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        } else if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        if (!checkPlace(x1, x2, y1, y2, len)) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        } else if (isCloseShip(x1, x2, y1, y2)) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        placeShipOnBoard(x1, x2, y1, y2, c);
        printField(playerField);
        return true;
    }

    private boolean checkPlace(int x1, int x2, int y1, int y2, int len) {

        boolean flag = false;

        if (x1 == x2 && Math.abs(y2 - y1) + 1 == len)
            flag = true;
        else if (y1 == y2 && Math.abs(x2 - x1) + 1 == len)
            flag = true;

        if (flag) {
            return true;
        }
        return false;
    }

    private boolean isCloseShip(int x1, int x2, int y1, int y2) {

        if (x1 == x2) {
            if (y1 - 1 >= 0 && playerField[x1][y1 - 1] != '~')
                return true;
            if (y2 + 1 <= 9 && playerField[x1][y2 + 1] != '~')
                return true;

            for (int i = y1; i <= y2; i++) {
                if (x1 - 1 >= 0 && playerField[x1 - 1][i] != '~')
                    return true;
                if (x1 + 1 <= 9 && playerField[x1 + 1][i] != '~')
                    return true;
            }
        } else {
            if (x1 - 1 >= 0 && playerField[x1 - 1][y1] != '~')
                return true;
            if (x2 + 1 <= 9 && playerField[x2 + 1][y1] != '~')
                return true;

            for (int i = x1; i <= x2; i++) {
                if (y1 - 1 >= 0 && playerField[i][y1 - 1] != '~')
                    return true;
                if (y1 + 1 <= 9 && playerField[i][y1 + 1] != '~')
                    return true;
            }
        }
        return false;
    }

    private boolean checkRow(char x1, char x2) {
        if ('A' <= x1 && x1 <= 'J' && 'A' <= x2 && x2 <= 'J') {
            return true;
        }
        return false;
    }

    private boolean checkColumn(int y1, int y2) {
        if (1 <= y1 && y1 <= 10 && 1 <= y2 && y2 <= 10) {
            return true;
        }
        return false;
    }

    private void arrangeDestroyer(char c) {
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");

        String cor1 = null;
        String cor2 = null;
        do {
            Scanner scanner = new Scanner(System.in);
            cor1 = scanner.next();
            cor2 = scanner.next();
        } while (!checkCoordinates(cor1, cor2, 2, c));
    }

    private void arrangeCruiser(char c) {
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");

        String cor1 = null;
        String cor2 = null;
        do {
            Scanner scanner = new Scanner(System.in);
            cor1 = scanner.next();
            cor2 = scanner.next();
        } while (!checkCoordinates(cor1, cor2, 3, c));
    }

    private void arrangeSubmarine(char c) {
        System.out.println("Enter the coordinates of the Submarine (3 cells):");

        String cor1 = null;
        String cor2 = null;
        do {
            Scanner scanner = new Scanner(System.in);
            cor1 = scanner.next();
            cor2 = scanner.next();
        } while (!checkCoordinates(cor1, cor2, 3, c));
    }

    private void arrangeBattleship(char c) {
        System.out.println("Enter the coordinates of the Battleship (4 cells):");

        String cor1 = null;
        String cor2 = null;
        do {
            Scanner scanner = new Scanner(System.in);
            cor1 = scanner.next();
            cor2 = scanner.next();
        } while (!checkCoordinates(cor1, cor2, 4, c));
    }

    private void arrangeAircraftCarrier(char c) {
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        String cor1 = null;
        String cor2 = null;
        do {
            Scanner scanner = new Scanner(System.in);
            cor1 = scanner.next();
            cor2 = scanner.next();
        } while (!checkCoordinates(cor1, cor2, 5, c));
    }

    private void placeShipOnBoard(int x1, int x2, int y1, int y2, char c) {
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++)
                playerField[x1][i] = c;
        } else {
            for (int i = x1; i <= x2; i++)
                playerField[i][y1] = c;
        }
    }

}