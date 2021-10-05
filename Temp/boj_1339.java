package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 반례 참고: https://www.acmicpc.net/board/view/67481

public class boj_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] alphabet = new Integer[26];
        Arrays.fill(alphabet, 0);

        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            for (int j = 0; j < word.length; j++) {
                alphabet[word[j] - 'A'] += (int) Math.pow(10, (word.length - j) - 1);
            }
        }

        Arrays.sort(alphabet, Collections.reverseOrder());

        int result = 0;
        int number = 9;
        for (Integer value : alphabet) {
            if (value == 0) break;
            result += (value * number);
            number--;
        }

        System.out.println(result);
    }
}
