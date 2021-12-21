package GroupStudy.December.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * blocks: 하단을 기준으로 빈 칸의 높이가 얼마인지를 저장함.
 * blocks[블록종류][회전타입][높이]
 */

public class boj_3019 {
    static int[][][] blocks = {
            {},
            {{0}, {0, 0, 0, 0}},
            {{0, 0}},
            {{0, 0, 1}, {1, 0}},
            {{1, 0, 0}, {0, 1}},
            {{0, 0, 0}, {0, 1}, {1, 0}, {1, 0, 1}},
            {{0, 0, 0}, {2, 0}, {0, 0}, {0, 1, 1}},
            {{0, 0, 0}, {0, 2}, {1, 1, 0}, {0, 0}},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());   // 열의 수
        int P = Integer.parseInt(st.nextToken());   // 떨어뜨리는 블록 종류

        int[] columns = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            columns[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int[][] block = blocks[P];
        for (int[] type : block) {
            for (int i = 0; i <= C - type.length; i++) {
                boolean isValid = true;
                int height = columns[i] - type[0];
                for (int j = 0; j < type.length; j++) {
                    if (height != (columns[i + j] - type[j])) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
