package GroupStudy.December.week4;

class programmers_211228 {
    public int solution(int n, int[][] wires) {
        int answer = 10000000;
        
        boolean[][] graph = new boolean[n+1][n+1];
        for(int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            graph[v1][v2] = true;
            graph[v2][v1] = true;
        }
        
        for(int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            boolean[] visited = new boolean[n+1];
            graph[v1][v2] = false;
            graph[v2][v1] = false;
            int groupA = dfs(v1, n, graph, visited, 1);
            int groupB = n - groupA;
            graph[v1][v2] = true;
            graph[v2][v1] = true;
            
            answer = Math.min(answer, Math.abs(groupA - groupB));
        }
        
        return answer;
    }
    
    private int dfs(int start, int n, boolean[][] graph, boolean[] visited, int count) {
        visited[start] = true;
            
        for(int i=1; i<=n; i++) {
            if(graph[start][i] && !visited[i]) {
                count = dfs(i, n, graph, visited, count+1);
            }
        }
        
        return count;
    }
}
