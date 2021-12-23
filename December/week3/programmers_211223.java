package GroupStudy.December.week3;

import java.util.*;

public class programmers_211223 {
    public int solution(String s) {
        char[] charArr = s.toCharArray();
        String result = "";
        String temp = "";

        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        for (char c : charArr) {
            if (c >= '0' && c <= '9') {
                if (temp.length() >= 1)
                    result += map.get(temp);
                result += c;
                temp = "";
            } else {
                if (map.containsKey(temp)) {
                    result += map.get(temp);
                    temp = "";
                }
                temp += c;
            }
        }

        if (temp.length() >= 1) {
            result += map.get(temp);
        }

        return Integer.parseInt(result);
    }
}
