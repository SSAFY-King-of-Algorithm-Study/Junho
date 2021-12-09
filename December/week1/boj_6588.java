package GroupStudy.December.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 에라토스테네스의 체 활용해서 미리 소수 배열을 구해놓고 시작.
 */
public class boj_6588 {
    static final int MAX_SIZE = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        boolean[] numbers = new boolean[MAX_SIZE + 1];
        for (int i = 2; i <= MAX_SIZE; i++) {
            // 이미 방문했던 곳이라면 continue
            if (numbers[i]) continue;

            // 아니라면 2부터 시작해서 배수들을 다 지워준다.
            int factor = 1;
            while (i * factor <= MAX_SIZE) {
                if (factor >= 2)
                    numbers[i * factor] = true;
                factor++;
            }
        }

        while (true) {
            int input = Integer.parseInt(br.readLine());

            // input == 0 이면 입력 종료
            if (input == 0) break;

            // 골드바흐의 추측 (3부터 시작해서 범위를 좁혀나감)
            boolean find = false;
            for (int i = 3; i <= MAX_SIZE / 2; i++) {
                if (!numbers[i] && !numbers[input - i]) {
                    find = true;
                    sb.append(input).append(" ").append("= ").append(i).append(" + ").append(input - i).append("\n");
                    break;
                }
            }

            if (!find) {
                sb.append("Goldbach's conjecture is wrong.\n");
            }
        }

        System.out.println(sb.toString());
    }
}
