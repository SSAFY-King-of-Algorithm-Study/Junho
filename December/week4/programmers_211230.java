import java.util.*;

// set 사용해서 중복처리
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        
        int turn = 2;
        
        Set<String> set = new HashSet<>();
        String current = words[0];
        set.add(current);
        
        for(int i=1; i<words.length; i++) {
            String next = words[i];
            
            if(current.charAt(current.length()-1) != next.charAt(0)) {
                answer[0] = turn;
                answer[1] = i/n + 1;
                return answer;
            }
            
            if(set.contains(next)) {
                answer[0] = turn;
                answer[1] = i/n + 1;
                return answer;
            }
            
            turn++;
            if(turn > n) turn = 1;
            current = next;
            set.add(current);
        }
        
        return answer;
    }
}
