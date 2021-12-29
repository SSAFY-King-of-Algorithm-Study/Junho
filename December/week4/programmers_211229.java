/*
    k = 유저의 현재 피로도
    dungeon[0] = 최소 필요 피로도 / dungeon[1] = 소모 피로도
    최대 던전 수를 return
*/
class Solution {
    static int answer = -1;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        findMaxDungeons(k, visited, dungeons, 0);
        return answer;
    }
    
    private void findMaxDungeons(int k, boolean[] visited, int[][] dungeons, int count) {
        if(k <= 0) { 
            answer = Math.max(answer, count);
            return;
        }
        
        for(int i=0; i<dungeons.length; i++) {
            int required = dungeons[i][0];
            int cost = dungeons[i][1];
            
            if(!visited[i] && k >= required) {
                visited[i] = true;
                findMaxDungeons(k-cost, visited, dungeons, count+1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, count);
        return;
    }
}
