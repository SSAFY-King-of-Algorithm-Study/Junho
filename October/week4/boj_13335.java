package GroupStudy.October.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 트럭 수
        int bridgeLength = Integer.parseInt(st.nextToken());   // 다리 길이
        int maxWeight = Integer.parseInt(st.nextToken());   // 최대 하중

        // 트럭 초기화
        st = new StringTokenizer(br.readLine());
        Queue<Integer> readyQueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            readyQueue.offer(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridgeLength; i++) {
            bridge.offer(0);    // 더미 데이터
        }

        int time = 0;
        int totalWeight = 0;
        while (true) {
            // 기다리는 트럭이 없고 + 다리 위가 비어있다면 모든 과정이 종료된 상황
            if (readyQueue.isEmpty() && bridge.isEmpty()) break;

            // 다리 위에 있는 트럭 먼저 이동
            totalWeight -= bridge.poll();
            // 대기하고있는 트럭 중 다리 위로 올 수 있다면
            if (!readyQueue.isEmpty()) {
                int truck = readyQueue.peek();
                if (totalWeight + truck <= maxWeight) {
                    readyQueue.poll();
                    bridge.offer(truck);
                    totalWeight += truck;
                } else {
                    bridge.offer(0);
                }
            }

            time++;
        }

        System.out.println(time);
    }
}
