import java.util.*;
// 3차원 방문 배열 사용: [방향][행][열]
// 0:동 1:남 2:서 3:북
class Solution {
    static int[] drow = {0, 1, 0, -1};
    static int[] dcol = {1, 0, -1, 0};
    
    public int[] solution(String[] grid) {
        // 3중 for-문 순회하면서 방문하지 않은 곳만 사이클 탐색 진행
        int row = grid.length;
        int col = grid[0].length();
        List<Integer> temp = new ArrayList<>();
        boolean[][][] visited = new boolean[4][row][col];
        for(int dir=0; dir<4; dir++) {
            for(int r=0; r<row; r++) {
                for(int c=0; c<col; c++) {
                    if(!visited[dir][r][c]) {
                        temp.add(findCycle(dir,r,c,row,col,grid,visited));
                    }
                }
            }
        }
        
        // List -> 오름차순 정렬 -> Array
        return temp.stream().sorted().mapToInt(o -> o).toArray();
    }
    
    private int findCycle(int dir, int r, int c, int row, int col, String[] grid, boolean[][][] visited) {
        int length = 0;
        int currentDir = dir;
        int currentRow = r;
        int currentCol = c;
        
        while(!visited[currentDir][currentRow][currentCol]) {
            // 방문 처리
            visited[currentDir][currentRow][currentCol] = true;
            
            // 이동할 다음 칸
            int nextRow = currentRow + drow[currentDir];
            int nextCol = currentCol + dcol[currentDir];
            
            // 이동 + 방향 및 좌표 변경 + 거리 증가
            // 만약, 범위를 나간다면 좌표 재지정
            if(isOut(nextRow, nextCol, row, col)) {
                // 위로 나가는 경우
                if(nextRow < 0) nextRow = row-1;
                // 아래로 나가는 경우
                if(nextRow >= row) nextRow = 0;
                // 오른쪽으로 나가는 경우
                if(nextCol >= col) nextCol = 0;
                // 왼쪽으로 나가는 경우
                if(nextCol < 0) nextCol = col-1;
            }
            currentRow = nextRow;
            currentCol = nextCol;
            currentDir = findDirection(currentDir, grid[nextRow].charAt(nextCol));
            length++;
        }
        
        return length;
    }
    
    private int findDirection(int currentDir, char c) {        
        if(c == 'L') {
            currentDir--;
            if(currentDir < 0) currentDir = 3;
            return currentDir;
        }
        if(c == 'R') {
            return (++currentDir)%4;
        }
        return currentDir;
    }
    
    private boolean isOut(int r, int c, int row, int col) {
        return r>=0 && r<row && c>=0 && c<col ? false : true;
    }
}
