package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cleaner {
    int row, col, dir;

    public Cleaner(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}

public class boj_14503 {
    static int N, M, count;
    static Cleaner cleaner;

    static int[][] board;

    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        cleaner = new Cleaner(row, col, dir);

        // 0: 빈칸, 1: 벽 + 2: 청소한 칸
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        simulation();
        System.out.println(count);
    }

    public static void simulation() {
        while (true) {
            if(board[cleaner.row][cleaner.col] == 0) {
                board[cleaner.row][cleaner.col] = 2;
                count++;
            }
            // 현재 방향 기준 반시계 방향으로 탐색 시작
            boolean find = false;
            for (int i = 1; i <= 4; i++) {
                int ndir = calcDir(cleaner.dir, 1);
                int nrow = cleaner.row + drow[ndir];
                int ncol = cleaner.col + dcol[ndir];

                // 청소하지 않은 공간이라면
                if (!isOut(nrow, ncol) && board[nrow][ncol] == 0) {
                    cleaner = new Cleaner(nrow,ncol,ndir);
                    find = true;
                    break;
                }
                // 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전
                cleaner.dir = ndir;
            }
            // 인접 네 방향 모두 청소가 되어있거나 벽인 경우
            if (!find) {
                int back = calcDir(cleaner.dir, 2);
                int backRow = cleaner.row + drow[back];
                int backCol = cleaner.col + dcol[back];
                // 1. 뒤쪽 방향이 벽이여서 후진을 못하는 경우 시뮬레이션 종료
                if (board[backRow][backCol] == 1) {
                    break;
                }
                // 2. 그게 아니라면 바라보는 방향을 유지한 채 한 칸 후진하고 탐색 다시 시작.
                cleaner.row = backRow;
                cleaner.col = backCol;
            }
        }
    }

    public static int calcDir(int dir, int rotateCnt) {
        return dir - rotateCnt < 0 ? (4 - Math.abs(dir - rotateCnt)) : dir - rotateCnt;
    }

    public static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < M) return false;
        return true;
    }
}
