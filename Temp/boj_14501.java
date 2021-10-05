package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Counsel {
    int time;
    int price;

    public Counsel(int time, int price) {
        this.time = time;
        this.price = price;
    }
}

public class boj_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Counsel[] counsels = new Counsel[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            counsels[i] = new Counsel(t, p);
        }

        int[] dp = new int[N + 2];
        int result = 0;

        // 현재 기준 "이전 날짜 중" 가능한 날짜 중 가장 큰 값을 선택
        for (int i = 1; i <= N; i++) {
            // 현재 날짜는 가능한지?
            if (i + counsels[i].time <= N + 1) {
                dp[i] += counsels[i].price;
            }

            int temp = 0;
            for (int j = 1; j < i; j++) {
                // 이전 날짜 중 가능한 날짜 찾기
                if (j + counsels[j].time <= i) {
                    temp = Math.max(temp, dp[j]);
                }
            }
            dp[i] += temp;
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
