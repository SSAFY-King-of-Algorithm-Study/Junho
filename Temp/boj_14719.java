package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14719 {
    static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        blocks = new int[W];
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(st.nextToken());
            blocks[i] = height;
        }

        int result = 0;
        for (int i = 1; i < W - 1; i++) {
            // 좌 우 탐색
            int leftHighest = find(0, i);
            int rightHighest = find(i + 1, W);

            if (leftHighest > blocks[i] && rightHighest > blocks[i])    // if문 빼먹음
                result += (leftHighest < rightHighest ? leftHighest - blocks[i] : rightHighest - blocks[i]);
        }

        System.out.println(result);
    }

    private static int find(int start, int end) {
        int highest = Integer.MIN_VALUE;

        for (int i = start; i < end; i++) {
            highest = Math.max(highest, blocks[i]);
        }

        return highest;
    }
}
