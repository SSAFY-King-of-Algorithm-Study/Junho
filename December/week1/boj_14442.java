package GroupStudy.December.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
    int row, col, dist, breakWall;

    public Info(int row, int col, int dist, int breakWall) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.breakWall = breakWall;
    }
}

public class boj_14442 {
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        bfs(board);
    }

    private static void bfs(char[][] board) {
        Queue<Info> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K + 1];

        queue.offer(new Info(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Info current = queue.poll();

            // 도착했다면 종료
            if (current.row == N - 1 && current.col == M - 1) {
                System.out.println(current.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nrow = current.row + drow[i];
                int ncol = current.col + dcol[i];

                // 범위를 나가지 않고
                if (!isOut(nrow, ncol)) {
                    // 벽이 아닌 경우
                    if (board[nrow][ncol] == '0' && !visited[nrow][ncol][current.breakWall]) {
                        visited[nrow][ncol][current.breakWall] = true;
                        queue.offer(new Info(nrow, ncol, current.dist + 1, current.breakWall));
                    }
                    // 벽인 경우 + 벽을 부술 수 있다면
                    else if (board[nrow][ncol] == '1' && current.breakWall < K) {
                        if (!visited[nrow][ncol][current.breakWall + 1]) {
                            visited[nrow][ncol][current.breakWall + 1] = true;
                            queue.offer(new Info(nrow, ncol, current.dist + 1, current.breakWall + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
        return;
    }

    private static boolean isOut(int row, int col) {
        return (row >= 0 && row < N && col >= 0 && col < M) ? false : true;
    }
}
