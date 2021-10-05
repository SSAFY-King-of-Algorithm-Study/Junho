package GroupStudy;

import java.util.*;

public class 메뉴리뉴얼 {

    // map: 메뉴조합, 주문횟수
    static Map<String, Integer> map = new HashMap<>();
    // countTable: N개의 요리조합으로 만든 코스의 최대 주문 횟수를 저장하기 위한 배열
    // 예) countTable[2개의 요리조합] = 4;
    static int[] countTable;

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> answerList = new ArrayList<>();    // answer에 몇 개가 들어갈지를 모르니까 List사용

        countTable = new int[course[course.length - 1] + 1];    // 인덱스가 "코스 구성 요리 개수"를 의미하게끔 크기 설정
        for (String o : orders) {
            // 주문이 문자 오름차순으로 들어오는게 아니여서 한번 정렬을 해준 후 조합을 생성해야함.
            char[] StringtoChar = o.toCharArray();
            Arrays.sort(StringtoChar);
            String order = new String(StringtoChar);

            // 코스 구성 요리 개수에 맞게 조합 생성
            for (int R : course) {
                char[] temp = new char[R];
                combination(0, 0, R, order, temp);
            }
        }

        // 인자로 넘어온 course와 map을 순회하면서 가장 많이 주문된 코스들을 answerList에 넣어준다.
        for (int i = 0; i < course.length; i++) {
            int menuNum = course[i];
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                Integer value = map.get(key);

                // 가장 많은 주문을 받은 메뉴조합이라면 answer에 넣어준다.
                if (key.length() == menuNum && countTable[menuNum] >= 2 && value == countTable[menuNum]) {
                    answerList.add(key);
                }
            }
        }

        // 정렬
        Collections.sort(answerList);

        // answer에 삽입
        answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public static void combination(int cnt, int start, int R, String order, char[] temp) {
        if (cnt == R) {
            String comb = new String(temp);
            // map에 없다면 새로 추가
            if (!map.containsKey(comb)) {
                map.put(comb, 1);
            }
            // map에 있다면 value++ 및 countTable 최댓값 갱신
            else {
                map.put(comb, map.get(comb) + 1);
                countTable[R] = Math.max(countTable[R], map.get(comb));
            }
            return;
        }

        for (int i = start; i < order.length(); i++) {
            temp[cnt] = order.charAt(i);
            combination(cnt + 1, i + 1, R, order, temp);
        }

    }

}
