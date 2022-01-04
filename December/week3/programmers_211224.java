//// BFS
//import java.util.*;
//
//class Pos {
//    int row, col;
//
//    Pos(int row, int col) {
//        this.row = row;
//        this.col = col;
//    }
//}
//
//class Solution {
//    static int[] drow = {-1,1,0,0};
//    static int[] dcol = {0,0,-1,1};
//
//    public int[] solution(int m, int n, int[][] picture) {
//        int numberOfArea = 0;
//        int maxSizeOfOneArea = 0;
//
//        int[] answer = new int[2];
//        answer[0] = numberOfArea;
//        answer[1] = maxSizeOfOneArea;
//
//        boolean[][] visited = new boolean[m][n];
//        for(int i=0; i<m; i++) {
//            for(int j=0; j<n; j++) {
//                if(!visited[i][j] && picture[i][j] > 0) {
//                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i,j,picture,visited,m,n));
//                    numberOfArea++;
//                }
//            }
//        }
//
//        answer[0] = numberOfArea;
//        answer[1] = maxSizeOfOneArea;
//        return answer;
//    }
//
//    private static int bfs(int row, int col, int[][] picture, boolean[][] visited, int m, int n) {
//        Queue<Pos> queue = new LinkedList<>();
//        visited[row][col] = true;
//        queue.offer(new Pos(row,col));
//
//        int area = 1;
//        while(!queue.isEmpty()) {
//            Pos current = queue.poll();
//            int color = picture[current.row][current.col];
//
//            for(int i=0; i<4; i++) {
//                int nrow = current.row + drow[i];
//                int ncol = current.col + dcol[i];
//                // 범위를 나가지 않고, 방문하지 않았고, 색이 같다면
//                if(!isOut(nrow, ncol, m, n) && !visited[nrow][ncol] && color == picture[nrow][ncol]) {
//                    visited[nrow][ncol] = true;
//                    queue.offer(new Pos(nrow,ncol));
//                    area++;
//                }
//            }
//        }
//
//        return area;
//    }
//
//    private static boolean isOut(int row, int col, int m, int n) {
//        return row>=0 && row<m && col>=0 && col<n ? false : true;
//    }
//}
