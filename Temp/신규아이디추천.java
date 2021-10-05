package GroupStudy;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class 신규아이디추천 {
    static String valid = "-_.";

    public static String solution(String new_id) {
        String answer = "";
        Queue<Character> q = new LinkedList<>();

        // 1단계
        String id = new_id.toLowerCase(Locale.ROOT);

        // 2단계
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (Character.isLowerCase(c) || Character.isDigit(c) || valid.contains(Character.toString(c))) {
                q.offer(c);
            }
        }

        // Queue => String
        while (!q.isEmpty()) {
            answer += q.poll();
        }

        // 3단계
        while (answer.contains("..")) {
            answer = answer.replace("..", ".");
        }

        // 4단계
        if (answer.length() >= 1) {
            if (answer.charAt(0) == '.') answer = answer.substring(1);
        }
        if (answer.length() >= 1) {
            if (answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, answer.length() - 1);
        }

        // 5단계
        if (answer.length() == 0) {
            answer = "a";
        }

        // 6단계
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, answer.length() - 1);
            }
        }

        // 7단계
        char last = answer.charAt(answer.length() - 1);
        if (answer.length() <= 2) {
            while (answer.length() < 3) {
                answer += last;
            }
        }
        return answer;
    }

}
