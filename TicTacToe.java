import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }

        char player = 'X';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            try {
                printBoard(board);
                System.out.print("Player " + player + ", enter your move (row and column): ");

                int row = scanner.nextInt();
                int col = scanner.nextInt();
                System.out.println();

                // Check for valid input range
                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Invalid input. Please enter numbers between 1 and 3 inclusive!");
                    continue; // Ask for input again
                }

                // Adjust to zero-based index
                row--;
                col--;

                if (board[row][col] == ' ') {
                    board[row][col] = player; // place the element
                    gameOver = haveWon(board, player);
                    if (gameOver) {
                        printBoard(board); // Print final board before announcing winner
                        System.out.println("Player " + player + " has won!");
                    } else {
                        player = (player == 'X') ? 'O' : 'X'; // Switch player
                    }
                } else {
                    System.out.println("Invalid move. That cell is already occupied! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter only two space-separated numbers between 1 and 3 inclusive!\n");
                scanner.next();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\nRow and column must be between 1 and 3 inclusive! Try again.\n");
            }
        }
        scanner.close();
    }

    public static boolean haveWon(char[][] board, char player) {
        // Check the rows
        for (char[] chars : board) {
            if (chars[0] == player && chars[1] == player && chars[2] == player) {
                return true;
            }
        }

        // Check for columns
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    public static void printBoard(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " | ");
            }
            System.out.println();
        }
    }
}
