package GroupStudy.October.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        int flagL = left, flagR = right, minDiff = Integer.MAX_VALUE;
        while (left < right) {
            if (arr[left] + arr[right] == 0) {
                System.out.println(arr[left] + " " + arr[right]);
                return;
            }

            // 0보다 큰 경우
            if (arr[left] + arr[right] > 0) {
                // 차이가 더 줄어든다면 기록을 남겨준다.
                int diff = Math.abs(arr[left] + arr[right]);
                if (minDiff > diff) {
                    flagL = left;
                    flagR = right;
                    minDiff = diff;
                }
                right--;
            }
            // 0보다 작은 경우
            else if (arr[left] + arr[right] < 0) {
                // 차이가 더 줄어든다면 기록을 남겨준다.
                int diff = Math.abs(arr[left] + arr[right]);
                if (minDiff > diff) {
                    flagL = left;
                    flagR = right;
                    minDiff = diff;
                }
                left++;
            }
        }

        System.out.println(arr[flagL] + " " + arr[flagR]);
    }
}
