package GroupStudy.November.week2;

import java.util.Scanner;

// 로직
// 1. DP 배열을 채워나가는 방식은 순환구조를 무시하고 구한다.
// 2. 최종적으로 답을 구할 때는 순환구조를 염두한 점화식을 활용한다.
public class boj_2482 {
    static final int DIVIDE = 1000000003;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[][] dp = new int[N + 1][N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        // DP 배열 채우기 (순환구조 무시)
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % DIVIDE;
            }
        }

        // 순환구조 염두한 점화식
        System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % DIVIDE);
    }
}