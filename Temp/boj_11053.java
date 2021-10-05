package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        int result = dp[0];
        for (int i = 1; i < N; i++) {
            int maxLength = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    maxLength = Math.max(maxLength, dp[j]);
            }
            dp[i] = maxLength + 1;
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
