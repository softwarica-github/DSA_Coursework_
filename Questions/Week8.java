package Questions;


import java.util.Deque;
import java.util.LinkedList;

public class Week8 {
    int [][]directions = {{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        Deque<int[]> q = new LinkedList<>();
        int count=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                char ch = grid[i].charAt(j);
                if(ch == '@') {
                    q.offer(new int[] {i,j,0,0});
                } else if (ch>='a' && ch <= 'f') {
                    count++;
                }
            }
        }
        int keys = (1<<count)-1;
        int [][][]visited = new int[m][n][keys +1];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int steps = cur[2];;
            int key = cur[3];
            if (key == keys) return steps;

            for (int[] direction: directions) {
                int newX = direction[0] + x;
                int newY = direction[1] + y;

                if (newX >=0 && newX < m && newY >=0 && newY<n&&
                        grid[newX].charAt(newY) != '#') {
                    char ch = grid[newX].charAt(newY);
                    if (ch >= 'A' && ch <= 'F') {
                        if(((key>>(ch - 'A')) & 1) == 1 && visited[newX][newY][keys]==0) {
                            visited[newX][newY][key] = 1;
                            q.offer(new int[] {newX, newY, steps+1,key});
                        }
                    }else if (ch>='a' && ch <='f') {
                        int newKey = key | (1 <<(ch-'a'));
                        if (visited[newX][newY][newKey] == 0) {
                            visited[newX][newY][newKey] = 1;
                            q.offer(new int[] {newX, newY, steps+1,newKey});
                        }
                    } else {
                        if (visited[newX][newY][key] == 0) {
                            visited[newX][newY][key] = 1;
                            q.offer(new int[] {newX, newY, steps+1,key});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Week8 obj = new Week8();

        String []grid = new String[] {"@*a*#","###*#","b*A*B"};
        System.out.println(obj.shortestPathAllKeys(grid));
    }
}
