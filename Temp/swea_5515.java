package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_5515 {
    static int[] months = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int days = d;
            for (int i = 1; i < m; i++) {
                days += months[i];
            }

            sb.append("#").append(tc).append(" ");
            int result = days % 7;
            if (result == 0) sb.append(3).append("\n");
            else if (result == 1) sb.append(4).append("\n");
            else if (result == 2) sb.append(5).append("\n");
            else if (result == 3) sb.append(6).append("\n");
            else if (result == 4) sb.append(0).append("\n");
            else if (result == 5) sb.append(1).append("\n");
            else if (result == 6) sb.append(2).append("\n");
        }

        System.out.println(sb);
    }
}
