package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1756 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        if (D < N) {
            System.out.println(0);
            return;
        }

        int[] oven = new int[D + 1];

        // 오븐 지름 입력 받으면서 갱신해준다.
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= D; i++) {
            int diameter = Integer.parseInt(st.nextToken());
            if (i == 1) {
                oven[i] = diameter;
                continue;
            }

            oven[i] = Math.min(oven[i - 1], diameter);
        }

        // 반죽이 들어갈 수 있는지 확인
        int last = D;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int pizza = Integer.parseInt(st.nextToken());

            // 반죽이 들어갈 수 있을때까지 jump
            while (last >= 1 && oven[last] < pizza)
                last--;

            // 반죽이 들어갈 수 있다면
            last--;
        }

        System.out.println(last < 1 ? 0 : last + 1);
    }
}
