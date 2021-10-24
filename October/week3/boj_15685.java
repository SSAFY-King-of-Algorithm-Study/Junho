package GroupStudy.October.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Line {
    int x, y, dir;

    public Line(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class boj_15685 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        board = new boolean[101][101];
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            board[y][x] = true;
            board[y + dy[d]][x + dx[d]] = true;

            if (g > 0) {
                List<Line> list = new ArrayList<>();
                list.add(new Line(x, y, d));
                dragonCurve(0, g, list);
            }
        }

        int result = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1])
                    result++;
            }
        }

        System.out.println(result);
    }

    private static void dragonCurve(int count, int g, List<Line> list) {
        if (count == g) {
            for (Line line : list) {
                int nx = line.x + dx[line.dir];
                int ny = line.y + dy[line.dir];
                if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100)
                    board[ny][nx] = true;
            }

            return;
        }

        int size = list.size();

        int pivotX = list.get(list.size() - 1).x + dx[list.get(list.size() - 1).dir];
        int pivotY = list.get(list.size() - 1).y + dy[list.get(list.size() - 1).dir];

        for (int i = size - 1; i >= 0; i--) {
            int ndir = (list.get(i).dir + 1) % 4;
            list.add(new Line(pivotX, pivotY, ndir));
            pivotX += dx[ndir];
            pivotY += dy[ndir];
        }

        dragonCurve(count + 1, g, list);
    }

}
