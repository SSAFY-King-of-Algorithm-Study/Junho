package GroupStudy.December.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_19583 {
    static int S, E, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken().replace(":", ""));
        E = Integer.parseInt(st.nextToken().replace(":", ""));
        Q = Integer.parseInt(st.nextToken().replace(":", ""));

        // Set 사용 -> 입장시 add -> 퇴장시 입장여부확인 후, count+1
        Map<String, Boolean> map = new HashMap<>();
        String input = null;
        int result = 0;
        while ((input = br.readLine()) != null) {
            String[] temp = input.split(" ");

            int time = Integer.parseInt(temp[0].replace(":", ""));
            String name = temp[1];

            // 입장 시간을 준수한 경우
            if (time <= S) {
                map.put(name, false);
            }

            // 퇴장 시간을 준수한 경우 + 입장한 적이 있다면
            else if (time >= E && time <= Q && map.containsKey(name) && !map.get(name)) {
                map.put(name, true); // 여러번 채팅 친 경우 중복 카운팅을 방지하기 위함.
                result++;
            }
        }

        System.out.println(result);
    }
}
