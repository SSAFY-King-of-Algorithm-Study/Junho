package GroupStudy.October.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 삼각형 초기화
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 다이나믹 프로그래밍
        int[][] dp = new int[n + 1][n + 1];
        dp[1][1] = triangle[1][1];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                // 대각선 왼쪽 아래
                dp[i + 1][j] = Math.max(dp[i + 1][j], triangle[i + 1][j] + dp[i][j]);
                // 대각선 오른쪽 아래
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], triangle[i + 1][j + 1] + dp[i][j]);
            }
        }

        // 제일 마지막 행에서 최댓값 추출
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, dp[n][i]);
        }

        System.out.println(result);
    }
}
