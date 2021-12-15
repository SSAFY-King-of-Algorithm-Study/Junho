package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj_2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 터널에 들어온 차
        String[] enter = new String[N];
        for (int i = 0; i < N; i++) {
            String car = br.readLine();
            enter[i] = car;
        }

        // 터널을 나간 차
        Map<String, Integer> exit = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String car = br.readLine();
            exit.put(car, i);
        }

        int current = -1;
        int result = 0;

        for (String car : enter) {
            // 추월한 경우
            if (current > exit.get(car)) result++;
            else if (current < exit.get(car)) {
                current = exit.get(car);
            }
        }

        System.out.println(result);
    }
}
