package GroupStudy.October.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pos {
    int row, col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class boj_17136 {
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[10][10];
        List<Pos> cells = new ArrayList<>();
        int remain = 0;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cells.add(new Pos(i, j));
                    remain++;
                }
            }
        }

        // 기저조건
        if (remain == 0) {
            System.out.println(0);
            return;
        }

        result = Integer.MAX_VALUE;
        check(0, remain, cells, board);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static boolean check(int count, int remain, List<Pos> cells, int[][] board) {
        if (remain == 0) {
            result = Math.min(result, count);
            return true;
        }

        for (Pos cell : cells) {
            if (board[cell.row][cell.col] == 0) continue;

            // 큰 종이부터 붙여본다.
            for (int size = 5; size >= 1; size--) {
                if (papers[size] < 1) continue;
                // 종이를 붙일 수 있다면
                if (isValid(cell.row, cell.col, size, board)) {
                    putPaper(cell.row, cell.col, size, board);
                    papers[size]--;
                    check(count + 1, remain - (size * size), cells, board);
                    removePaper(cell.row, cell.col, size, board);
                    papers[size]++;
                }
            }
            return false;   // 그 칸에 붙일 수 있는게 없다는 것이므로 이전 상황으로 돌아가야한다.
        }

        return false;
    }

    private static void putPaper(int row, int col, int size, int[][] board) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = 0;
            }
        }
    }

    private static void removePaper(int row, int col, int size, int[][] board) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = 1;
            }
        }
    }

    private static boolean isValid(int row, int col, int size, int[][] board) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (isOut(i, j) || board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < 10 && col < 10) return false;
        return true;
    }
}
