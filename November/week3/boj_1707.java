package GroupStudy.November.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 초기화
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            int[] visited = new int[V + 1];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // bfs를 통한 인접 그래프 여부 확인
            boolean isValid = false;
            for (int from = 1; from <= V; from++) {
                if (visited[from] == 0) {
                    isValid = bfs(from, graph, visited, V);
                    if (!isValid) {
                        System.out.println("NO");
                        break;
                    }
                }
            }

            if (isValid) System.out.println("YES");
        }
    }

    private static boolean bfs(int node, List<List<Integer>> graph, int[] visited, int V) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited[node] = 1;

        while (!queue.isEmpty()) {
            int from = queue.poll();
            int group = visited[from];

            for (Integer to : graph.get(from)) {
                if (visited[to] >= 0 && group == visited[to]) {
                    return false;
                }
                if (visited[to] == 0) {
                    visited[to] = (group == 1 ? 2 : 1);
                    queue.offer(to);
                }
            }
        }

        return true;
    }

}
