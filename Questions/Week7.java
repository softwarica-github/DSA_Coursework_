package Questions;
import java.util.*;


public class Week7 {
    // function to find path with tiger
    public static int pathwithTiger(int[][] A){
      int x = A.length;
      int y = A[0].length;
      // creating a matrix starting at position 0
      int[][] paths = new int[x][y];
      // loop for finding paths
      for (int i = 0; i < x; i++) {
        Arrays.fill(paths[i], -1);
      }
      return uniquepaths(0, 0, x, y, A, paths);
    }
   // finding unique paths to avoid tiger
    public static int uniquepaths(int i, int j, int x,int y, int[][] A, int[][] paths){
      // boundary condition or constraints
      if (i == x || j == y) {
        return 0;
      }
      else if (A[i][j] == 1) {
        return 0;
      }
      // base case
      else if (i == x - 1 && j == y - 1) {
        return 1;
      }
      else if (paths[i][j] != -1) {
        return paths[i][j];
      }
      else {
        return uniquepaths(i + 1, j, x, y, A, paths) + uniquepaths(i, j + 1, x, y, A, paths);
      }
    }

    public static void main(String[] args){
      // inserting the given grid 
      int[][] A = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
      System.out.println("The number of different pathways is" + pathwithTiger(A));
    }
  }