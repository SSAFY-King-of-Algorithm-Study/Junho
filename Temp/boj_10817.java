package GroupStudy;

import java.util.Arrays;
import java.util.Scanner;

public class boj_10817 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[3];
        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();
        nums[2] = sc.nextInt();
        Arrays.sort(nums);
        System.out.println(nums[1]);
    }
}
