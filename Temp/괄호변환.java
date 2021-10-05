package GroupStudy;

import java.util.Stack;

public class 괄호변환 {

    // 올바른 괄호 문자열인지 확인하는 메소드
    boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) stack.push(s.charAt(i));
            else {
                char top = stack.peek();
                char current = s.charAt(i);
                if (top == '(' && current == ')') {
                    stack.pop();
                } else {
                    stack.push(current);
                }
            }
        }

        if (stack.isEmpty()) return true;
        return false;
    }

    // 재귀
    String recursive(String s) {
        // 1. 입력이 빈 문자열인 경우 빈 문자열을 반환
        if (s.length() == 0) return s;

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        String u = "";
        String v = "";
        int open = 0, close = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                open++;
                u += current;
            }
            if (current == ')') {
                close++;
                u += current;
            }
            // 균형잡혔다면 v는 자동적으로 정해진다.
            if (open == close) {
                v = s.substring(i + 1);
                break;
            }
        }

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행
        if (isValid(u)) {
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + recursive(v);
        }

        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행
        else {
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            String temp = "(";
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            temp += recursive(v);
            // 4-3. ')'를 다시 붙입니다.
            temp += ')';
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            u = u.substring(1, u.length() - 1);
            String reversed_U = "";
            for (int i = 0; i < u.length(); i++) {
                char current = u.charAt(i);
                if (current == '(')
                    reversed_U += ')';
                else
                    reversed_U += '(';
            }
            temp += reversed_U;
            return temp;
        }
    }

    public String solution(String p) {
        // 이미 올바른 괄호 문자열이라면 바로 반환
        if (isValid(p)) return p;

        String answer = recursive(p);
        return answer;
    }
}
