package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dust {
    int row, col, amount;

    public Dust(int row, int col, int amount) {
        this.row = row;
        this.col = col;
        this.amount = amount;
    }
}

public class boj_17144 {
    static int R, C, T;
    static List<Integer> airCleaner;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 공기청정기 위치 및 미세먼지 정보를 저장하는 큐 초기화
        Queue<Dust> dusts = new LinkedList<>();
        airCleaner = new ArrayList<>();   // 공기청정기 위, 아래 좌표 저장(행)
        for (int row = 1; row <= R; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= C; col++) {
                int type = Integer.parseInt(st.nextToken());
                // 공기청정기인 경우
                if (type == -1) {
                    airCleaner.add(row);
                }
                // 미세먼지인 경우
                if (type > 0) {
                    dusts.offer(new Dust(row, col, type));
                }
            }
        }

        // T초간 시뮬레이션 진행
        while (T-- > 0) {
            spreadDust(dusts);
            runAirCleaner(dusts);
        }

        // 결과 출력
        int result = 0;
        while (!dusts.isEmpty()) {
            result += dusts.poll().amount;
        }
        System.out.println(result);
    }

    private static void spreadDust(Queue<Dust> dusts) {
        int N = dusts.size();
        int[][] board = new int[R + 1][C + 1];

        for (int i = 0; i < N; i++) {
            Dust current = dusts.poll();
            int spreadCount = 0;
            // 인접한 네 방향으로 확산 (단, 바깥으로 나가거나 공기청정기가 있으면 제외)
            for (int j = 0; j < 4; j++) {
                int nrow = current.row + drow[j];
                int ncol = current.col + dcol[j];
                if (!isOut(nrow, ncol) && !(airCleaner.contains(nrow) && ncol == 1)) {
                    board[nrow][ncol] += current.amount / 5;
                    spreadCount++;
                }
            }
            board[current.row][current.col] += current.amount - ((current.amount / 5) * spreadCount);
        }

        // 갱신된 board내의 미세먼지 정보들을 다시 큐에 삽입
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                if (board[row][col] > 0) {
                    dusts.offer(new Dust(row, col, board[row][col]));
                }
            }
        }
    }

    private static void runAirCleaner(Queue<Dust> dusts) {
        int N = dusts.size();
        for (int i = 0; i < N; i++) {
            Dust current = dusts.poll();
            int row = current.row;
            int col = current.col;
            int amount = current.amount;

            // 위쪽 공기청정기 => 반시계
            if (row == airCleaner.get(0) && (col > 1 && col < C))   // 아래
                current = new Dust(row, col + 1, amount);
            if ((row > 1 && row <= airCleaner.get(0)) && col == C)  // 오른쪽
                current = new Dust(row - 1, col, amount);
            if (row == 1 && (col > 1 && col <= C))                  // 위
                current = new Dust(row, col - 1, amount);
            if ((row >= 1 && row < airCleaner.get(0)) && col == 1)  // 왼쪽
                current = new Dust(row + 1, col, amount);

            // 아래쪽 공기청정기 => 시계
            if (row == airCleaner.get(1) && (col > 1 && col < C))   // 위
                current = new Dust(row, col + 1, amount);
            if ((row < R && row >= airCleaner.get(1)) && col == C)  // 오른쪽
                current = new Dust(row + 1, col, amount);
            if (row == R && (col > 1 && col <= C))                  // 아래
                current = new Dust(row, col - 1, amount);
            if ((row <= R && row > airCleaner.get(1)) && col == 1)  // 왼쪽
                current = new Dust(row - 1, col, amount);

            // 공기청정기로 들어가는 경우
            if (airCleaner.contains(current.row) && current.col == 1) continue;

            dusts.offer(current);
        }
    }

    private static boolean isOut(int row, int col) {
        if (row >= 1 && col >= 1 && row <= R && col <= C) return false;
        return true;
    }
}

