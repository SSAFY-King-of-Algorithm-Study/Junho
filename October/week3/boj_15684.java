package GroupStudy.October.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15684 {
    static int N, M, H;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new boolean[H + 1][N + 1];

        // 초기 가로선 설치
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 가로선 설치
            board[a][b] = true;
        }

        // 가로선 최대 3개
        for (int i = 0; i <= 3; i++) {
            recursive(1, 0, i);
        }

        System.out.println(-1);
    }

    private static void recursive(int startRow, int count, int target) {
        if (count == target) {
            // 사다리 타기 진행
            for (int i = 1; i <= N; i++) {
                int temp = simulation(i);
                if (i != temp) return;
            }

            System.out.println(count);
            System.exit(0);
        }

        for (int row = startRow; row <= H; row++) {
            for (int col = 1; col <= (N - 1); col++) {
                // 가로선을 설치할 수 있다면 설치해준다.
                if (!board[row][col] && !board[row][col + 1]) {
                    board[row][col] = true;
                    recursive(row, count + 1, target);
                    board[row][col] = false;
                }
            }
        }

    }

    private static int simulation(int i) {
        int col = i;
        for (int row = 1; row <= H; row++) {
            if (board[row][col])
                col++;
            else if ((col - 1) >= 1 && board[row][col - 1])
                col--;
        }

        return col;
    }
}
