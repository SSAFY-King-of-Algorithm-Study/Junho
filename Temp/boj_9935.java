package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] str = br.readLine().toCharArray();
        String explosionStr = br.readLine();    // 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다. (각 문자는 고유함을 의미)

        Stack<String> stack = new Stack<>();

        for (char c : str) {

            // 기저조건: explosionStr의 길이가 1인 경우
            if(explosionStr.equals(Character.toString(c))) continue;

            if (!stack.isEmpty()) {
                String top = stack.pop();

                // 새로 들어오려는 문자가 explosionStr의 마지막 문자인 경우 => 이전까지 쌓인 explosionStr과 동일한 문자열 자체를 pop
                if (explosionStr.equals(top + c)) continue;

                // 이어 붙여서 다시 넣어준다.
                if (explosionStr.startsWith(top + c)) {
                    stack.push(top + c);
                    continue;
                }

                stack.push(top);
            }
            // 2. 그게 아니거나 explosionStr의 첫 문자인 경우는 그냥 스택에 넣어준다.
            stack.push(Character.toString(c));
        }

        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.length() >= 2) {
                // 임시 StringBuilder를 사용하여 뒤집어서 넣어준다. (폭발문자열이 될뻔한 문자들은 길이가 2이상이므로, 미리 뒤집어서 넣어줘야지 출력할때 똑바로 나온다.)
                StringBuilder temp = new StringBuilder();
                temp.append(top);
                sb.append(temp.reverse());
                continue;
            }
            sb.append(top);
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
            return;
        }
        System.out.println(sb.reverse());
    }
}
