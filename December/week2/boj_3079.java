package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전체 시간을 기준으로 이분탐색 진행
 *
 * 놓친 부분
 * 1. "입국심사대 > 인원수" 라고 해도 그게 최선이 아닌 경우가 존재한다.
 * 2. long 사용해야 함.
 * 3. 최소시간인 경우를 계속 찾아봐야 한다.
 *    예를 들어, 13초에도 처리할 수 있는 인원이 10명이고 12초에도 처리할 수 있는 인원이 10명이면
 *    13초에 걸린다고 이분탐색을 멈추는게 아닌 12초까지도 볼 수 있게끔 구현해야 함.
 */
public class boj_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 입국심사대
        long M = Integer.parseInt(st.nextToken());   // 인원 수

        int[] immigration = new int[N];
        long MAX_TIME = 0;   // 최대 소요 시간을 저장하기 위한 변수
        for (int i = 0; i < N; i++) {
            immigration[i] = Integer.parseInt(br.readLine());
            MAX_TIME = Math.max(MAX_TIME, immigration[i]);
        }

        long left = 1;
        long right = MAX_TIME * M;

        long result = right;
        while (left <= right) {
            long total = 0;
            long mid = (left + right) / 2;

            for (int time : immigration) {
                total += (mid / time);
            }
            
            if (total < M) left = mid + 1;
            if (total >= M) {
                result = Math.min(result, mid);
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
