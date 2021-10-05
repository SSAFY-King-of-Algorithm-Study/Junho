package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 반례 참고: https://www.acmicpc.net/board/view/54067

public class boj_1701 {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        result = 0;
        for(int i=0; i<s.length()-1; i++) {
            String substr = s.substring(i);
            makePi(substr);
        }
        System.out.println(result);
    }

    private static void makePi(String s) {
        int[] pi = new int[s.length()];

        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];

            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = ++j;
                result = Math.max(result, pi[i]);
            }
        }
    }
}
