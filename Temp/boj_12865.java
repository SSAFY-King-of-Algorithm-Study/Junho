package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Stuff {
    int w, v;

    public Stuff(int w, int v) {
        this.w = w;
        this.v = v;
    }
}

public class boj_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];
        Stuff[] stuffs = new Stuff[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            stuffs[i] = new Stuff(W, V);
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                // 무게를 못담는 경우
                if (stuffs[i].w > w) {
                    dp[i][w] = dp[i - 1][w];
                }
                // 담을 수 있는 경우
                else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - stuffs[i].w] + stuffs[i].v);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
