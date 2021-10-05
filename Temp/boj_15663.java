package GroupStudy;

import java.util.*;

public class boj_15663 {
    static boolean[] visited;
    static int[] nums;
    static int[] temp;
    static int N, M;

    static Set<String> set;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        nums = new int[N];
        visited = new boolean[N];
        temp = new int[M];

        set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);
        permutation(0);

        List<String> list = new ArrayList<>(set);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] s1 = o1.split(" ");
                String[] s2 = o2.split(" ");

                return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
            }
        });
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void permutation(int cnt) {
        if (cnt == M) {
            String s = "";
            for (int num : temp) {
                s = s.concat(Integer.toString(num) + " ");
            }
            // 중복검사
            if (!set.contains(s)) {
                set.add(s);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            temp[cnt] = nums[i];
            visited[i] = true;
            permutation(cnt + 1);
            visited[i] = false;
        }

    }
}
