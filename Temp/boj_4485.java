package GroupStudy;
/*
    백트래킹 vs 다익스트라,,,,
    비슷할것같은데 백트래킹 재귀 스택 터져서 못할듯?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
    int row, col, cost;

    public Pos(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pos o) {
        return this.cost - o.cost;
    }
}

public class boj_4485 {
    static final int INF = 10000000;
    static int N;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            // 배열 및 비용 테이블 초기화
            int[][] board = new int[N][N];      // 원본 배열
            int[][] costTable = new int[N][N];  // 비용 저장 배열
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = line.charAt(j) - '0';
                    costTable[i][j] = INF;
                }
            }

            // 다익스트라
            PriorityQueue<Pos> pq = new PriorityQueue<>();
            pq.offer(new Pos(0, 0, board[0][0]));
            costTable[0][0] = board[0][0];

            while (!pq.isEmpty()) {
                Pos current = pq.poll();

                // 만약, current가 도착지점이라면
                if (current.row == N - 1 && current.col == N - 1) {
                    sb.append("#").append(tc).append(" ").append(current.cost).append("\n");
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nrow = current.row + drow[i];
                    int ncol = current.col + dcol[i];

                    // 경계영역을 벗어나지 않고
                    if (!isOut(nrow, ncol)) {
                        int ncost = costTable[current.row][current.col] + board[nrow][ncol];
                        // 갱신해도 된다면
                        if (costTable[nrow][ncol] > ncost) {
                            pq.offer(new Pos(nrow, ncol, ncost));
                            costTable[nrow][ncol] = ncost;
                        }
                    }
                }
            }
        }
        System.out.println(sb);
    }

    private static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < N) return false;
        return true;
    }
}
