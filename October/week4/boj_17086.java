package GroupStudy.October.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    int row, col, dist;

    public Pos(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "row=" + row +
                ", col=" + col +
                ", dist=" + dist +
                '}';
    }
}

public class boj_17086 {

    static int result;
    static int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // board 초기화
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0인 칸에서 bfs 진행
        result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    bfs(new Pos(i, j, 0), board, new boolean[N][M]);
                }
            }
        }

        System.out.println(result == Integer.MIN_VALUE ? Math.max(N, M) : result);
    }

    private static void bfs(Pos start, int[][] board, boolean[][] visited) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.row][start.col] = true;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            if (board[current.row][current.col] == 1) {
                result = Math.max(result, current.dist);
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nrow = current.row + drow[i];
                int ncol = current.col + dcol[i];

                if (nrow >= 0 && ncol >= 0 && nrow < N && ncol < M && !visited[nrow][ncol]) {
                    queue.offer(new Pos(nrow, ncol, current.dist + 1));
                    visited[nrow][ncol] = true;
                }
            }
        }

    }
}
