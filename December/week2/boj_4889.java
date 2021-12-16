package GroupStudy.December.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_4889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String input = "";
        int no = 0;
        while (!((input = br.readLine()).contains("-"))) {
            int change = 0;

            Stack<Character> stack = new Stack<>();
            for (char c : input.toCharArray()) {
                if (stack.isEmpty()) {
                    if (c == '}') {
                        c = '{';
                        change++;
                    }
                    stack.push(c);
                    continue;
                }

                char top = stack.peek();
                if (top == '{' && c == '}') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            // stack에 남아있는 요소가 있다면 (로직상 '{'로만 구성되어있음.)
            if (!stack.isEmpty()) {
                change += stack.size() / 2;
            }

            sb.append(++no).append(". ").append(change).append("\n");
        }

        System.out.println(sb.toString());
    }
}
