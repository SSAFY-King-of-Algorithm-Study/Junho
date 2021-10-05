package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int no, dist;

    public Node(int no, int dist) {
        this.no = no;
        this.dist = dist;
    }
}

public class boj_20924 {
    static int N, R;
    static Map<Integer, List<Node>> tree;
    static int maxLength;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 트리 초기화 (무방향 간선)
        tree = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (tree.get(start) == null) {
                List<Node> temp = new ArrayList<>();
                tree.put(start, temp);
            }
            if (tree.get(end) == null) {
                List<Node> temp = new ArrayList<>();
                tree.put(end, temp);
            }
            List<Node> temp1 = tree.get(start);
            List<Node> temp2 = tree.get(end);
            temp1.add(new Node(end, dist));
            temp2.add(new Node(start, dist));
            tree.put(start, temp1);
            tree.put(end, temp2);
        }

        // 기저조건
        if (N == 1) {
            System.out.println(0 + " " + 0);
            return;
        }

        // 기둥 길이 구하면서 + 기가노드 찾기
        int current = R;    // 현재 노드 번호
        int trunk = 0;      // 나무 기둥의 길이
        int gigaNode = R;   // 기가 노드 번호
        boolean onlyTrunk = false;  // 기둥만 있는 케이스를 확인하기 위한 변수
        visited = new boolean[N + 1];
        while (true) {
            // 기가노드인 경우 (루트노드가 바로 기가노드가 되는데, 뻗어나가는 가지가 2개인 경우도 존재)
            if (tree.get(current).size() >= 3 || (current == R && tree.get(current).size() >= 2)) {
                gigaNode = current;
                break;
            }

            // 기둥 검사
            List<Node> nodeList = tree.get(current);
            boolean isLeaf = true;
            for (Node node : nodeList) {
                if (!visited[node.no]) {
                    trunk += node.dist;
                    visited[current] = true;
                    current = node.no;
                    isLeaf = false;
                }
            }

            // 리프노드인 경우
            if (isLeaf) {
                gigaNode = current;
                onlyTrunk = true;
                break;
            }
        }

        if (onlyTrunk) {
            System.out.printf("%d %d", trunk, 0);
            return;
        }

        // 기가 노드 기점으로 가지들의 길이를 구하면서 최소 길이를 찾는다.
        List<Node> branches = tree.get(gigaNode);
        visited[gigaNode] = true;
        maxLength = Integer.MIN_VALUE;
        for (Node node : branches) {
            dfs(node.no, node.dist);
        }

        System.out.printf("%d %d", trunk, maxLength);
    }

    private static void dfs(int nodeNo, int totalDist) {
        // 리프 노드인 경우 종료
        if (tree.get(nodeNo).size() == 1) {
            maxLength = Math.max(maxLength, totalDist);
            return;
        }

        List<Node> branches = tree.get(nodeNo);
        visited[nodeNo] = true;
        for (Node node : branches) {
            if (!visited[node.no])
                dfs(node.no, totalDist + node.dist);
        }
    }
}
