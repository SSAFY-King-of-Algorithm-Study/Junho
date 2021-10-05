package GroupStudy;

public class 합승택시요금 {
    static int[][] graph;
    static int[] together;

    // n: 정점의 수, s: 시작 지점, a: a의 집, b: b의 집, fares: 비용
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // 그래프 기본 초기화
        graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = (i == j) ? 0 : (200 * 100000) + 1;
                // 틀린 부분: 원래는 100,001로 했는데, 일직선으로 연결된 경우 최대 200*100,000의 비용이 발생
                //          그리고 원래 Integer.MAX_VALUE 사용했는데 이러면 플로이드-워셜할때 오버플로우 발생.
            }
        }

        // fares 읽어서 그래프 초기화
        for (int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int cost = fares[i][2];

            graph[x][y] = cost;
            graph[y][x] = cost; //  양방향이여서 넣어줘야하는데, 이거 빼먹음...
        }

        // 플로이드-워셜 (1. 혼자갈때 최소비용(graph 이용), 2. 합승할때 최소비용(together 이용))
        together = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    graph[x][y] = Math.min(graph[x][y], graph[x][i] + graph[i][y]);
                }
            }
        }

        // 시작지점에서 i 지점까지 합승했을 때 최소 비용을 저장하기 위함. (사실 이렇게 따로 안빼도 상관없음.)
        for (int i = 1; i <= n; i++) {
            together[i] = graph[s][i];
        }

        // 최소 비용 계산
        // 방법: 특정 지점까지 합승 비용 + 혼자 택시 이용 => 모든 케이스에 다 적용될 수 있음.
        for (int i = 1; i <= n; i++) {
            int total = 0;
            // i 지점까지 합승
            total += together[i];
            // 혼자 택시 이용
            total += graph[i][a] + graph[i][b];
            answer = Math.min(answer, total);
        }

        return answer;
    }
}
