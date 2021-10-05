package GroupStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Operation {
    int time;
    char dir;

    public Operation(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }
}

class Pair {
    int row;
    int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class boj_3190 {
    static int N;
    static int[] drow = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dcol = {0, 1, 0, -1};
    static boolean[][] board;
    static Deque<Pair> snake;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int result = 1;

        // board 초기화
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];

        // 사과 위치 초기화
        int K = Integer.parseInt(br.readLine());
        while (K > 0) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            board[row][col] = true;
            K--;
        }

        // 방향 전환 명령문 (큐(연결리스트)로 구현하는게 더 효율적일듯 -> 리스트로하면 ArrayDoubling으로 인한 성능저하 여지 있을듯?)
        int L = Integer.parseInt(br.readLine());
        Queue<Operation> operations = new LinkedList<>();
        while (L > 0) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            operations.offer(new Operation(time, dir));
            L--;
        }

        // 뱀 위치 및 방향 초기화
        snake = new LinkedList<>();
        snake.offer(new Pair(0, 0));
        int dir = 1;

        // 시뮬레이션 진행
        while (true) {
            Pair head = snake.getFirst();
            int nrow = head.row + drow[dir];
            int ncol = head.col + dcol[dir];

            // 맵과 충돌하면 종료
            if (isOut(nrow, ncol)) {
                break;
            }
            // 자기 자신과 충돌하면 종료
            if (collision(nrow, ncol)) {
                break;
            }
            // 사과가 있다면
            if (findApple(nrow, ncol)) {
                snake.addFirst(new Pair(nrow, ncol));
                board[nrow][ncol] = false;
            } else {
                snake.addFirst(new Pair(nrow, ncol));
                snake.pollLast();
            }

            // 방향 전환
            Operation op = operations.peek();
            if (op != null && op.time == result) {
                if (op.dir == 'L') {
                    dir--;
                    if (dir < 0) {
                        dir = 3;
                    }
                } else if (op.dir == 'D') {
                    dir = (dir + 1) % 4;
                }
                operations.poll();
            }

            result++;
        }

        System.out.print(result);
    }

    public static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < N && col < N)
            return false;
        return true;
    }

    public static boolean findApple(int row, int col) {
        if (board[row][col])
            return true;
        return false;
    }

    public static boolean collision(int row, int col) {
        for (Pair pair : snake) {
            int bodyRow = pair.row;
            int bodyCol = pair.col;

            if (bodyRow == row && bodyCol == col)
                return true;
        }
        return false;
    }
}
