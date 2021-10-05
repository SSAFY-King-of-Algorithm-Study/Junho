package GroupStudy.October.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 로직
// 1. 조합으로 두 개의 선거구로 분리
// 2. 분리된 두 선거구가 타당한지 확인 => bfs
// 3. 타당하다면 계산

public class boj_17471 {
    static int N;
    static int[] table;
    static int total;
    static int result;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        table = new int[N + 1];
        total = 0;

        // 각 지역 인구 수 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            table[i] = Integer.parseInt(st.nextToken());
            total += table[i];
        }

        // 인접한 구역 정보 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i] = new ArrayList<>();
            int adjCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adjCnt; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 지역구 조합 생성
        result = Integer.MAX_VALUE;
        for (int i = 1; i <= N / 2; i++) {
            List<Integer> groupA = new ArrayList<>();
            combination(1, i, groupA);
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void combination(int start, int count, List<Integer> groupA) {
        // 다 뽑은 경우
        if (count == 0) {
            // B 지역구 생성
            List<Integer> groupB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (!groupA.contains(i)) {
                    groupB.add(i);
                }
            }

            // 두 지역구 구성이 타당한지
            if (isValid(groupA) && isValid(groupB)) {
                result = Math.min(result, calculation(groupA));
            }

            return;
        }

        for (int i = start; i <= N; i++) {
            groupA.add(i);
            combination(i + 1, count - 1, groupA);
            groupA.remove(groupA.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> group) {
        int node = group.get(0);
        int length = group.size();

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.offer(node);
        visited[node] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> adjList = graph[current];
            for (Integer adjNode : adjList) {
                if (!visited[adjNode] && group.contains(adjNode)) {
                    queue.offer(adjNode);
                    visited[adjNode] = true;
                    count++;
                }
            }
        }

        return count == length ? true : false;
    }

    private static int calculation(List<Integer> groupA) {
        int population = 0;
        for (Integer node : groupA) {
            population += table[node];
        }

        return Math.abs(population - (total - population));
    }

}
