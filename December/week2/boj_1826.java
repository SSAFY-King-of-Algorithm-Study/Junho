package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 고려해야할 부분:
 * 어떻게 가야지 최소로 도달할 수 있는지를 좀 더 고민해봐야할듯.
 */

class Station {
    int dist, oil;

    public Station(int dist, int oil) {
        this.dist = dist;
        this.oil = oil;
    }
}

public class boj_1826 {
    static Comparator<Station> sortByDistance = new Comparator<Station>() {
        @Override
        public int compare(Station o1, Station o2) {
            return o1.dist - o2.dist;
        }
    };

    static Comparator<Station> sortByOil = new Comparator<Station>() {
        @Override
        public int compare(Station o1, Station o2) {
            return -(o1.oil - o2.oil);
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Station> stations = new PriorityQueue<>(sortByDistance);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int oil = Integer.parseInt(st.nextToken());

            stations.offer(new Station(dist, oil));
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());   // 마을까지 거리
        int P = Integer.parseInt(st.nextToken());   // 초기 연료의 양 (현재 연료의 양)

        /**
         * stations에서 poll을 한다. 단, 현재 가지고 있는 연료로 갈 수 있는 거리까지만!
         * 갈 수 있는 주유소 중에서 가장 우선순위가 높은 곳으로 가고 result+1을 해준다.
         */
        int result = 0;
        PriorityQueue<Station> priorityQueue = new PriorityQueue<>(sortByOil);

        while (P < L) {
            findReachable(stations, priorityQueue, P);
            // 갈 수 있는 곳이 없다면 종료
            if (priorityQueue.isEmpty()) break;
            P += priorityQueue.poll().oil;
            result++;
        }

        System.out.println(P >= L ? result : -1);
    }

    private static void findReachable(PriorityQueue<Station> stations, PriorityQueue<Station> priorityQueue, int P) {
        while (!stations.isEmpty()) {
            Station front = stations.peek();
            if (front.dist <= P) {
                priorityQueue.offer(stations.poll());
            } else {
                break;
            }
        }
    }
}
