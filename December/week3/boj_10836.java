package GroupStudy.December.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_10836 {
    static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());    // 크기
        N = Integer.parseInt(st.nextToken());    // 날짜 수

        // 1로 초기화
        int[][] board = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(board[i], 1);
        }

        // 애벌레가 자라는 정도
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            for (int j = M - 1; j >= 0; j--) {
                if (zero != 0) {
                    zero--;
                } else if (one != 0) {
                    board[j][0] += 1;
                    one--;
                } else if (two != 0) {
                    board[j][0] += 2;
                    two--;
                }
            }
            for (int j = 1; j <= M - 1; j++) {
                if (zero != 0) {
                    zero--;
                } else if (one != 0) {
                    board[0][j] += 1;
                    one--;
                } else if (two != 0) {
                    board[0][j] += 2;
                    two--;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0)
                    sb.append(board[i][j]).append(" ");
                else
                    sb.append(board[0][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
