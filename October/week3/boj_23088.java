package GroupStudy.October.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Process implements Comparable<Process> {
    int no, t, p, b;

    public Process(int no, int t, int p, int b) {
        this.no = no;
        this.t = t;
        this.p = p;
        this.b = b;
    }

    @Override
    public int compareTo(Process o) {
        // 1. 우선순위 높을수록
        if (this.p > o.p) return -1;
        else {
            if (this.p == o.p) {
                // 2. 실행시간 짧을수록
                if (this.b < o.b) return -1;
                else {
                    if (this.b == o.b) {
                        // 3. 번호가 작은 프로세스
                        if (this.no < o.no) return -1;
                        else return 1;
                    } else return 1;
                }
            } else return 1;
        }
    }
}

public class boj_23088 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 호출 시점의 범위는 0~300,000
        List<Process>[] processList = new List[300001];

        int N = Integer.parseInt(br.readLine());
        for (int no = 1; no <= N; no++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 호출 시점에 해당하는 곳에 process 삽입
            p = 300000 - (t - p);
            Process process = new Process(no, t, p, b);
            if (processList[t] == null) {
                processList[t] = new ArrayList<>();
            }
            processList[t].add(process);
        }

        // 우선순위 큐를 활용한 우선순위 스케줄링 진행
        PriorityQueue<Process> pq = new PriorityQueue<>();
        int usableTime = -1;
        for (int time = 0; time <= 300000; time++) {
            if (processList[time] != null) {
                if (usableTime == -1) usableTime = time;    // 한번만 초기화 시켜주면 된다. => 처음 호출되는 프로세스의 시간이 0이 아닌 경우를 위함.
                for (Process process : processList[time]) {
                    pq.offer(process);
                }
            }

            if (!pq.isEmpty()) {
                // 우선순위 큐 맨앞의 프로세스를 처리해준다. (처리가능한 시간일때)
                if (time >= usableTime) {
                    Process current = pq.poll();
                    sb.append(current.no).append(" ");
                    usableTime += current.b;
                }
            }
        }

        /*
        반례) 가장 늦은 호출시간인 300000에서 3개의 프로세스가 요청을 보낼때 다음 while문으로 처리해줘야함.
            3
            300000 1 100
            300000 2 1000
            300000 1 1000
        */
        while (!pq.isEmpty()) {
            sb.append(pq.poll().no).append(" ");
        }

        System.out.print(sb);
    }
}











