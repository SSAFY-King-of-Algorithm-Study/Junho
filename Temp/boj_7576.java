package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    int row;
    int col;
    int day;

    public Tomato(int row, int col, int day) {
        this.row = row;
        this.col = col;
        this.day = day;
    }
}

public class boj_7576 {

    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Tomato> q = new LinkedList<>();

        // 토마토 몇개나 익었는지 확인
        int cnt = 0;

        // board 초기화 및 이미 익은 토마토들 큐에 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    q.offer(new Tomato(i, j, 0));
                    visited[i][j] = true;
                }
                if (board[i][j] == 0) cnt++;
            }
        }

        // bfs 진행
        int result = 0;
        while (!q.isEmpty()) {
            Tomato current = q.poll();
            for (int i = 0; i < 4; i++) {
                int nrow = current.row + drow[i];
                int ncol = current.col + dcol[i];
                if (!isOut(nrow, ncol) && !visited[nrow][ncol] && board[nrow][ncol] == 0) {
                    q.offer(new Tomato(nrow, ncol, current.day + 1));
                    visited[nrow][ncol] = true;
                    result = Math.max(result, current.day + 1);
                    cnt--;
                }
            }
        }

        if(cnt == 0)
            System.out.println(result);
        else
            System.out.println(-1);
    }

    public static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < M) return false;
        return true;
    }

}
