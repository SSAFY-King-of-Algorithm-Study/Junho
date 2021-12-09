package GroupStudy.December.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1956 {
    static final int INF = 10001;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 초기화 */
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[][] table = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(table[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            table[from][to] = cost;
        }

        /* 플로이드 워셜 */
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }

        /* 최소 비용 사이클 찾기 */
        int result = INF;
        for (int i = 1; i <= V; i++) {
            result = Math.min(result, table[i][i]);
        }

        System.out.println(result == INF ? -1 : result);
    }
}
