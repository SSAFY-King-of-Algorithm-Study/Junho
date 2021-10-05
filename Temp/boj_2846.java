package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2846 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] H = new int[N];
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int start = H[0];
        int end = H[0];
        boolean flag = true;
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (H[i] > H[i - 1]) {
                end = H[i];
            } else {
                results.add(end - start);
                start = H[i];
                end = H[i];
            }
        }

        if (start < end) {
            results.add(end - start);
        }

        if (results.size() == 0) System.out.println(0);
        else {
            Collections.sort(results, Collections.reverseOrder());
            System.out.println(results.get(0));
        }

    }
}
