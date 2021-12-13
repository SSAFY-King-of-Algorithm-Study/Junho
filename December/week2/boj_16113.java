package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_16113 {
    static final String[] NUMBERS = {"111111000111111", "11111", "101111010111101",
            "101011010111111", "111000010011111", "111011010110111",
            "111111010110111", "100001000011111", "111111010111111", "111011010111111"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        char[] encodedSignal = br.readLine().toCharArray();

        int row = 5;
        int col = size / 5;
        char[][] decodedSignal = new char[row][col];

        // 시그널 변환 (encoded -> decoded)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                decodedSignal[i][j] = encodedSignal[i * col + j] == '#' ? '1' : '0';
            }
        }

        // 해독 (열 단위로 진행)
        String result = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < col; i++) {
            // 공백이라면
            if (decodedSignal[0][i] == '0' && decodedSignal[1][i] == '0' && decodedSignal[2][i] == '0' && decodedSignal[3][i] == '0' && decodedSignal[4][i] == '0') {
                if (sb.length() > 0) {
                    result += convertSignal(sb.toString());
                    sb.setLength(0);
                }
                continue;
            }

            for (int j = 0; j < 5; j++) {
                sb.append(decodedSignal[j][i]);
            }
        }

        // 마지막에 처리 못하는 부분을 처리하기 위함.
        if (sb.length() > 0) {
            result += convertSignal(sb.toString());
            sb.setLength(0);
        }
        System.out.println(result);

    }

    private static String convertSignal(String signal) {
        for (int i = 0; i <= 9; i++) {
            if (NUMBERS[i].equals(signal))
                return String.valueOf(i);
        }
        return null;
    }

}
