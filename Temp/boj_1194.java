package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Minsik {
    int row, col, count, keys;

    public Minsik() {
    }

    public Minsik(int row, int col, int count, int keys) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.keys = keys;
    }
}

public class boj_1194 {
    static int N, M;
    static char[][] board;
    static boolean[][][] visited;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // board 초기화
        board = new char[N][M];
        visited = new boolean[N][M][64];
        Minsik minsik = new Minsik();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                // 민식이인 경우
                if (board[i][j] == '0') {
                    minsik = new Minsik(i, j, 0, 0);
                }
            }
        }

        result = Integer.MAX_VALUE;
        bfs(minsik);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void bfs(Minsik minsik) {
        // 탈출하거나, 더 이상 갈곳이 없다면 종료
        Queue<Minsik> queue = new LinkedList<>();
        queue.offer(minsik);
        visited[minsik.row][minsik.col][0] = true;

        while (!queue.isEmpty()) {
            Minsik current = queue.poll();

            // 탈출구라면
            if (board[current.row][current.col] == '1') {
                result = Math.min(result, current.count);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nrow = current.row + drow[i];
                int ncol = current.col + dcol[i];

                if (!isOut(nrow, ncol) && !visited[nrow][ncol][current.keys]) {
                    // 1. 빈 공간
                    if (board[nrow][ncol] == '.' || board[nrow][ncol] == '0' || board[nrow][ncol] == '1') {
                        queue.offer(new Minsik(nrow, ncol, current.count + 1, current.keys));
                        visited[nrow][ncol][current.keys] = true;
                    }

                    // 2. 열쇠
                    if (board[nrow][ncol] >= 'a' && board[nrow][ncol] <= 'f') {
                        int keys = 1 << (board[nrow][ncol] - 'a');
                        keys |= current.keys;

                        if (!visited[nrow][ncol][keys]) {
                            queue.offer(new Minsik(nrow, ncol, current.count + 1, keys));
                            visited[nrow][ncol][keys] = true;
                        }
                    }

                    // 3. 문
                    if (board[nrow][ncol] >= 'A' && board[nrow][ncol] <= 'F') {
                        int door = 1 << (board[nrow][ncol] - 'A');
                        int canOpen = door & current.keys;

                        if (canOpen > 0) {
                            queue.offer(new Minsik(nrow, ncol, current.count + 1, current.keys));
                            visited[nrow][ncol][current.keys] = true;
                        }
                    }
                }
            }
        }
    }


    private static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < M) return false;
        return true;
    }
}
