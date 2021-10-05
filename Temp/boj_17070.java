package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pipe {
    int row, col, mode;

    public Pipe(int row, int col, int mode) {
        this.row = row;
        this.col = col;
        this.mode = mode;
    }
}

public class boj_17070 {

    static int N;
    static int[][] board;

    static int[] drow = {0, 1, 1};
    static int[] dcol = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(board[N][N] == 1) {
            System.out.println(0);
            return;
        }

        Queue<Pipe> queue = new LinkedList<>();
        queue.offer(new Pipe(1, 2, 0));
        int result = 0;
        while (!queue.isEmpty()) {
            Pipe current = queue.poll();

            // 도착했다면
            if (current.row == N && current.col == N) result++;

            boolean horizontal = false;
            boolean vertical = false;
            for (int i = 0; i < 3; i++) {
                int nrow = current.row + drow[i];
                int ncol = current.col + dcol[i];

                if (!isOut(nrow, ncol) && board[nrow][ncol] == 0) {
                    if (i == 2 && horizontal && vertical)
                        queue.offer(new Pipe(nrow, ncol, i));

                    else if (i < 2) {
                        if (i == 0) {
                            horizontal = true;
                            if (current.mode != 1)
                                queue.offer(new Pipe(nrow, ncol, i));
                        }
                        if (i == 1) {
                            vertical = true;
                            if (current.mode != 0)
                                queue.offer(new Pipe(nrow, ncol, i));
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static boolean isOut(int row, int col) {
        if (row >= 1 && col >= 1 && row <= N && col <= N) return false;
        return true;
    }
}
