package GroupStudy.October.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] powArr = new int[320];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        powArr[1] = 1;

        // 제곱한 값을 갖는 배열 초기화
        for (int i = 1; i < 320; i++) {
            powArr[i] = (int) Math.pow(i, 2);
        }

        for (int i = 2; i <= N; i++) {
            // sqrt 적용시 결과가 정수라면 1
            if ((int) Math.sqrt(i) == Math.sqrt(i)) {
                dp[i] = 1;
                continue;
            }

            // 그게 아니라면
            for (int j = 1; j < 320; j++) {
                if (powArr[j] > i) break;
                dp[i] = Math.min(dp[i], 1 + dp[i - powArr[j]]);
            }
        }

        System.out.println(dp[N]);
    }
}
