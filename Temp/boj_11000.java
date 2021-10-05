package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Time implements Comparable<Time> {
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        if (this.start == o.start) {     // 시작 시간이 같다면 끝나는 시간 기준으로 오름차순 정렬
            return this.end - o.end;
        }
        return this.start - o.start;
    }
}

public class boj_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // Time 배열 초기화 및 오름차순 정렬
        Time[] times = new Time[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[i] = new Time(start, end);
        }
        Arrays.sort(times);

        // 우선순위큐 생성 및 초기화
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // default: 오름차순 정렬 => 가장 빨리 끝나는 강의가 맨앞에 있게된다.
        pq.offer(times[0].end);

        for (int i=1; i<N; i++) {
            // 연강이 가능하다면
            if (pq.peek() <= times[i].start) {
                pq.poll();
            }

            pq.offer(times[i].end);
        }

        System.out.print(pq.size());
    }
}
