package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 중복된 좌표의 센서를 처리해주기 위해서 set 사용
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        // 기저 조건1: 집중국 수 >= 센서의 개수
        if (set.size() <= K) {
            System.out.println(0);
            return;
        }

        // set -> list : 좌표 정렬하기 위함
        List<Integer> sensorList = new ArrayList<>(set);
        Collections.sort(sensorList);

        // 이웃한 좌표간의 간격들을 구해서 intervalList에 삽입
        List<Integer> intervalList = new ArrayList<>();
        for (int i = 0; i < sensorList.size() - 1; i++) {
            int start = sensorList.get(i);
            int end = sensorList.get(i + 1);
            int interval = end - start;
            intervalList.add(interval);
        }

        // K개의 집중국을 설치해야 한다 => 여러 개의 interval(간격) 중 K-1개의 interval을 제거하면 된다.
        int answer = 0;
        Collections.sort(intervalList);
        for (int i = 0; i < intervalList.size() - (K - 1); i++) {
            answer += intervalList.get(i);
        }

        System.out.println(answer);
    }
}
