package GroupStudy;

import java.util.Arrays;
import java.util.Scanner;

public class boj_3273 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // init
        int result = 0;
        int n = sc.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }
        int x = sc.nextInt();

        // two-pointer
        Arrays.sort(seq);
        int left = 0, right = n - 1;
        while (left < right) {
            if (seq[left] + seq[right] == x) {
                result++;
                left++;
                right--;
            }else if (seq[left] + seq[right] < x) {
                left++;
            }else if (seq[left] + seq[right] > x) {
                right--;
            }

        }
        System.out.print(result);
        sc.close();
        sc = null;
    }
}
