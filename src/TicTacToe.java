import java.util.Scanner;


public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            initializeBoard();
            currentPlayer = "X";
            boolean gameEnded = false;

            while (!gameEnded) {
                displayBoard();

                int rowMove, colMove;
                do {
                    System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] column [1-3]): ");
                    rowMove = SafeInput.getRangedInt(scanner, "", 1, 3) - 1;
                    colMove = SafeInput.getRangedInt(scanner, "", 1, 3) - 1;
                } while (!isValidMove(rowMove, colMove));

                board[rowMove][colMove] = currentPlayer;

                if (isWin(currentPlayer)) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isTie()) {
                    displayBoard();
                    System.out.println("It's a tie!");
                    gameEnded = true;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            System.out.println("Do you want to play again? (Yes/No): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("Yes")) {
                gameRunning = false;
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void displayBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            System.out.println("Invalid move! Row and column should be between 1 and 3.");
            return false;
        }
        if (!board[row][col].equals(" ")) {
            System.out.println("Cell already occupied. Try again.");
            return false;
        }
        return true;
    }

    private static boolean isWin(String player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true; // Row win
            }
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true; // Column win
            }
        }
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)); // Diagonal win
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // Not a tie, as there is an empty cell
                }
            }
        }
        return true; // All cells are filled, hence a tie
    }
}