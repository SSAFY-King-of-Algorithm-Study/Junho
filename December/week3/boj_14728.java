package GroupStudy.December.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info {
    int time, score;

    public Info(int time, int score) {
        this.time = time;
        this.score = score;
    }
}

public class boj_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 단원 수
        int T = Integer.parseInt(st.nextToken());   // 제한 시간

        int[][] dp = new int[N+1][T+1];
        List<Info> infoList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            infoList.add(new Info(time, score));
        }

//        Collections.sort(infoList, (o1, o2) -> o1.time - o2.time);  //  시간 순 정렬

        for(int i=1; i<=N; i++) {
            for(int j=0; j<=T; j++) {
                int time = infoList.get(i-1).time;
                int score = infoList.get(i-1).score;
                // 1. 공부할 시간이 없는 경우
                if(j < time) {
                    dp[i][j] = dp[i-1][j];
                }
                // 2. 공부할 시간이 있는 경우
                else {
                    dp[i][j] = Math.max(dp[i-1][j-time] + score, dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[N][T]);
    }
}