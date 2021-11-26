package GroupStudy.November.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    int row, col;

    public Pos() {
    }

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Animal {
    Pos pos;
    int time;

    public Animal() {
    }

    public Animal(Pos pos, int time) {
        this.pos = pos;
        this.time = time;
    }
}

public class boj_3055 {
    static int R, C;
    static char[][] board;
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Pos cave = new Pos();
        Animal animal = new Animal();
        Pos water = new Pos();

        Queue<Pos> waterQueue = new LinkedList<>(); // 물의 초기 위치가 여러 개일 수도 있었다...
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'D')
                    cave = new Pos(i, j);
                if (board[i][j] == 'S') {
                    animal = new Animal(new Pos(i, j), 0);
                    board[i][j] = '.';
                }
                if (board[i][j] == '*') {
                    water = new Pos(i, j);
                    waterQueue.offer(water);
                }
            }
        }

        bfs(cave, animal, waterQueue);
    }

    private static void bfs(Pos cave, Animal animal, Queue<Pos> waterQueue) {
        Queue<Animal> animalQueue = new LinkedList<>();
        animalQueue.offer(animal);

        boolean[][] visited = new boolean[R][C];
        visited[animal.pos.row][animal.pos.col] = true;

        int time = 0;
        while (!animalQueue.isEmpty()) {
            // 물을 먼저 퍼뜨려준다.
            spreadWater(waterQueue);

            int size = animalQueue.size();
            for (int loop = 0; loop < size; loop++) {
                Animal current = animalQueue.poll();
                // 굴에 도착했다면 출력

                if (current.pos.row == cave.row && current.pos.col == cave.col) {
                    System.out.println(time);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nrow = current.pos.row + drow[i];
                    int ncol = current.pos.col + dcol[i];
                    if (!isOut(nrow, ncol) && !visited[nrow][ncol] && (board[nrow][ncol] == '.' || board[nrow][ncol] == 'D')) {
                        Animal next = new Animal(new Pos(nrow, ncol), current.time + 1);
                        animalQueue.offer(next);
                        visited[nrow][ncol] = true;
                    }
                }
            }

            time++;
        }

        System.out.println("KAKTUS");
    }

    private static void spreadWater(Queue<Pos> waterQueue) {
        int size = waterQueue.size();
        for (int loop = 0; loop < size; loop++) {
            Pos currentWater = waterQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nrow = currentWater.row + drow[i];
                int ncol = currentWater.col + dcol[i];
                if (!isOut(nrow, ncol) && board[nrow][ncol] == '.') {
                    board[nrow][ncol] = '*';
                    waterQueue.offer(new Pos(nrow, ncol));
                }
            }
        }
    }

    private static boolean isOut(int row, int col) {
        if (row >= 0 && col >= 0 && row < R && col < C) return false;
        return true;
    }
}
