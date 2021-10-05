package GroupStudy;

import java.util.*;

public class 순위검색 {

    static Map<String, List<Integer>> map = new HashMap<>();
    static boolean[] visited = new boolean[4];

    public int[] solution(String[] info, String[] query) {


        // info 읽어서 부분집합 생성
        for (String s : info) {
            String[] strings = s.split("");
            subset(strings, 0);
        }

        // 이분 탐색을 위한 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            q = q.replace(" and", "");

            String[] parsed = q.split(" ");
            String key = "";
            for (int j = 0; j < 4; j++) {
                if (parsed[j].equals("-")) {
                    key += "";
                } else {
                    key += parsed[j];
                }
            }

            int score = Integer.parseInt(parsed[4]);
            List<Integer> scores = map.getOrDefault(key, new ArrayList<>());
            int s = 0;
            int l = scores.size();
            while(s < l) {
                int mid = (s+l) / 2;
                if(scores.get(mid) < score)
                    s = mid + 1;
                else
                    l = mid;
            }
            answer[i] = scores.size() - s;
        }
        return answer;
    }

    public void subset(String[] strings, int count) {
        if (count == 4) {
            String temp = "";
            for (int i = 0; i < 4; i++) {
                if (visited[i]) temp += strings[i];
                else temp += "";

                if (map.containsKey(temp)) {
                    map.get(temp).add(Integer.parseInt(strings[4]));
                } else {
                    map.put(temp, new ArrayList<>());
                }

                return;
            }
        }

        visited[count] = true;
        subset(strings, count + 1);
        visited[count] = false;
        subset(strings, count + 1);

    }
}
