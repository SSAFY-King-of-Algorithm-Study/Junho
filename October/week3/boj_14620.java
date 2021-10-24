package GroupStudy.October.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pos {
    int row, col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class boj_14620 {
    static int N;
    static Pos[] cells;
    static int[][] board;
    static int result;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        cells = new Pos[N * N];

        // 화단 비용 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합
        result = Integer.MAX_VALUE;
        combination(0, 0, new Pos[3]);
        System.out.println(result);
    }

    private static void combination(int start, int count, Pos[] temp) {
        if (count == 3) {
            int cost = 0;
            boolean[][] visited = new boolean[N][N];
            for (Pos cell : temp) {
                int row = cell.row;
                int col = cell.col;
                cost += board[row][col];
                visited[row][col] = true;

                // 꽃 피우기
                for (int i = 0; i < 4; i++) {
                    int nrow = row + drow[i];
                    int ncol = col + dcol[i];

                    // 꽃이 시드는 경우
                    if (isOut(nrow, ncol) || visited[nrow][ncol]) return;

                    cost += board[nrow][ncol];
                    visited[nrow][ncol] = true;
                }
            }

            result = Math.min(result, cost);
            return; // 이거 넣어줘야 index error 발생하지 않는다.
        }

        for (int i = start; i < N * N; i++) {
            temp[count] = cells[i];
            combination(i + 1, count + 1, temp);
        }
    }

    private static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < N) return false;
        return true;
    }
}
