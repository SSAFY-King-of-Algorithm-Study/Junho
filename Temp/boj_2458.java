package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            boolean[][] graph = new boolean[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph[v1][v2] = true;
            }

            // 플로이드-워셜
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        // i에서 k를 거쳐서 j로 가는 경로가 존재한다면
                        if (graph[i][k] && graph[k][j])
                            graph[i][j] = true;
                    }
                }
            }

            // 진입차수 + 진출차수 = N-1 (자기 자신을 제외한 나머지 학생과의 관계가 존재한다는 의미가 된다.)
            int[] students = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 진출차수와 진입차수 갱신
                    if (graph[i][j]) {
                        students[i]++;
                        students[j]++;
                    }
                }
            }

            int result = 0;
            for (int student : students) {
                if (student == N - 1)
                    result++;
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }
}
