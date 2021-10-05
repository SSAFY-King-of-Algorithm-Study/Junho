package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] applicants = new int[N + 1];    // 인덱스:서류성적 순위, 값:면접성적 순위
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int rankOfDocs = Integer.parseInt(st.nextToken());
                int rankOfIntvw = Integer.parseInt(st.nextToken());

                applicants[rankOfDocs] = rankOfIntvw;
            }

            int result = 1;
            int searchRange = applicants[1];
            for (int i = 2; i <= N; i++) {
                if (applicants[i] < searchRange) {
                    result++;
                    searchRange = applicants[i];
                }
            }

            System.out.println(result);
        }
    }
}
