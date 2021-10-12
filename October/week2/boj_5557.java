package GroupStudy.October.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int target = Integer.parseInt(st.nextToken());

        // row = N-1, col = 20 (0<= col<= 20)
        long[][] dp = new long[N - 1][21];
        dp[0][nums[0]] = 1;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] >= 1) {
                    int plus = j + nums[i + 1];
                    int minus = j - nums[i + 1];

                    if (plus >= 0 && plus <= 20)
                        dp[i + 1][plus] += dp[i][j];
                    if (minus >= 0 && minus <= 20)
                        dp[i + 1][minus] += dp[i][j];
                }
            }
        }

        // 출력: [마지막행][target]
        System.out.println(dp[N - 2][target]);
    }

}
