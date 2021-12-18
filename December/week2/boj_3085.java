package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_3085 {
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            board[i] = input;
        }

        // 2중-for 돌면서 색이 다른 인접한 칸 탐색
        int result = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                char current = board[row][col];
                for (int k = 0; k < 4; k++) {
                    int nrow = row + drow[k];
                    int ncol = col + dcol[k];
                    // 범위 체크 + 색이 다른지
                    if (!isOut(nrow, ncol, N) && board[nrow][ncol] != current) {
                        // 교환전에 몇개 먹을 수 있는지 확인
                        result = Math.max(result, countCandy(board, row, col, N));
                        // 교환을 하고
                        changeCandy(board, nrow, ncol, row, col);
                        // 몇개를 먹을 수 있는지 확인하고
                        result = Math.max(result, countCandy(board, row, col, N));
                        // 다시 원래대로 복구
                        changeCandy(board, nrow, ncol, row, col);
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static int countCandy(char[][] board, int row, int col, int N) {
        int maxCount = 1;

        // 행 검사
        char candy = ' ';
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (candy == board[row][i]) {
                count++;
                if (i == N - 1) maxCount = Math.max(maxCount, count);
            } else {
                maxCount = Math.max(maxCount, count);
                count = 1;
                candy = board[row][i];

            }
        }
        // 열 검사
        candy = ' ';
        count = 1;
        for (int i = 0; i < N; i++) {
            if (candy == board[i][col]) {
                count++;
                if (i == N - 1) maxCount = Math.max(maxCount, count);
            } else {
                maxCount = Math.max(maxCount, count);
                count = 1;
                candy = board[i][col];
            }
        }

        return maxCount;
    }

    private static void changeCandy(char[][] board, int nrow, int ncol, int row, int col) {
        char temp = ' ';
        temp = board[row][col];
        board[row][col] = board[nrow][ncol];
        board[nrow][ncol] = temp;
    }

    private static boolean isOut(int row, int col, int N) {
        return row >= 0 && row < N && col >= 0 && col < N ? false : true;
    }

}
