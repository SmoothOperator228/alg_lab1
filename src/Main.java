import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 20; i++){
            int[] board = new int[8];

            for (int j = 0; j < board.length; j++) {
                board[j] = (int) (Math.random() * 8);
            }

//        int[] board = {4, 4, 7, 3, 1, 1, 3, 1};

            System.out.println("Starting board is:");
            System.out.println(Arrays.toString(board));
            System.out.println("_______________________________________________________");
            System.out.println();

            long start = System.nanoTime();
            int[] solvedAStar = AStar.search(board).state;
            long end = System.nanoTime();

            long aStarRes = end - start;

//        start = System.nanoTime();
//        int[] solvedLDFS = LDFS.search(board).state;
//        end = System.nanoTime();
//
//        long ldfsRes = end - start;

            System.out.println(Arrays.toString(solvedAStar));
            System.out.println();
            System.out.println("time spent for A* search " + aStarRes + " ns");
            System.out.println("_______________________________________________________");
//        System.out.println();
//        System.out.println(Arrays.toString(solvedLDFS));
//        System.out.println();
//        System.out.println("time spent for LDFS search "+ ldfsRes + " ns");
        }
    }

    public static void boardVisualise(int[] board){
        int[][] matrix = new int[8][8];
        for (int i = 0; i < board.length; i++){
            int j = board[i];
            matrix[j][i] = 1;
        }

        for (int i = 0; i < matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
