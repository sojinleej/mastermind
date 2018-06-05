//package assign.assign1_mastermind;

import java.util.*;

public class Mastermind implements MastermindInterface {

    // 2D array for the game board
    public int[][] array = new int[10][8];
    // an array for the randomly generated four uniq digits
    public int[] ans = new int[4];
    public boolean gameStop = false;
    public int guessNum = 10;
    Scanner input = new Scanner(System.in);

    // draw the game board and print its array depends on the situation
    // if the game stops, it shows the ans array instead of four 'x'
    public void drawGame() {
        if (!gameStop) {
            System.out.println("*** You have " + guessNum + " chances. *** ");
        }
        System.out.println(" --------------- ");
        if (!gameStop) {
            System.out.print("| X | X | X | X |");
        } else {
            System.out.print("| " + ans[0] + " | " + ans[1] + " | "
                    + ans[2] + " | " + ans[3] + " |");
        }
        System.out.println();
        for (int outer = 0; outer < array.length; outer++) {
            System.out.println(" ---------------      ------ ");
            for (int inner = 0; inner < array[outer].length; inner++) {
                if (inner < 4) {
                    if (array[outer][inner] != 0) {
                        System.out.print("| " + array[outer][inner] + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (inner == 4) {
                    if (array[outer][inner] != 0) {
                        System.out.print("| == | " + array[outer][inner]);
                    } else if (array[outer][inner] == 0 && (guessNum <= outer)) {
                        System.out.print("| == | " + array[outer][inner]);

                    } else {
                        System.out.print("| == |  ");
                    }
                } else {
                    if (array[outer][inner] != 0) {
                        System.out.print(array[outer][inner]);
                    } else if (array[outer][inner] == 0 && (guessNum <= outer)) {
                        System.out.print(array[outer][inner]);
                    } else {
                        System.out.print(" ");
                    }
                }
                if (inner == array[outer].length - 1) {
                    System.out.print(" |");
                }
            }
            System.out.println();
            if (outer == 9) {
                System.out.println(" ---------------      ------ ");
            }
        }
    }

    // create randomly generated four uniq digits
    public void newGame() {

        int[] oneToEight = new int[8];
        int ranNum;
        int sum = 0;

        while (sum < 4) {
            for (int i = 0; i < 4; i++) {
                ranNum = (int) (Math.random() * 8) + 1;
                ans[i] = ranNum;
                oneToEight[ranNum - 1] = 1;
            }
            for (int x = 0; x < oneToEight.length; x++) {
                sum += oneToEight[x];
            }
            if (sum < 4) {
                for (int i = 0; i < oneToEight.length; i++) {
                    oneToEight[i] = 0;
                }
                sum = 0;
            }
        }
    }

    public void checkValues() {
        int guess;
        guessNum = 9;

        while (guessNum >= 0 && !gameStop) {
            System.out.print("Enter 4 digits using the digits 1 - 8 only "
                    + "with a space followed by each digit.");
//            for (int i = 0; i < array[guessNum].length; i++) {
            for (int i = 0; i < 4; i++) {

                guess = input.nextInt();
                array[guessNum][i] = guess;
            }
//            for (int i = 0; i < array[guessNum].length; i++) {
//                for (int j = 0; j < array[guessNum].length; j++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (array[guessNum][i] == ans[j]) {
                        if (array[guessNum][i] == ans[i]) {
                            array[guessNum][i + 4] = 2;
                        } else if (array[guessNum][i] != array[guessNum][j]) {
                            array[guessNum][i + 4] = 1;
                        }
                    }
                }
            }

            int temp;
//            for (int i = 1; i < array[guessNum].length; i++) {
            for (int i = 1; i < 4; i++) {
                for (int j = i; j > 0; j--) {
                    if (array[guessNum][j + 4] > array[guessNum][j + 4 - 1]) {
                        temp = array[guessNum][j + 4];
                        array[guessNum][j + 4] = array[guessNum][j + 4 - 1];
                        array[guessNum][j + 4 - 1] = temp;
                    }
                }
            }

            int matched = 0;
            for (int i = 4; i < array[guessNum].length; i++) {
                matched += array[guessNum][i];
            }

            if (guessNum <= 0 || matched == 8) {
                gameStop = true;
                System.out.println("*** The game is over ***");
            }
            updateGame();
            guessNum--;
        }
    }

    // update the game board
    public void updateGame() {
        drawGame();
    }

    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        System.out.println("Start the new game");
        mastermind.drawGame();
        mastermind.newGame();
        mastermind.checkValues();
    }
}