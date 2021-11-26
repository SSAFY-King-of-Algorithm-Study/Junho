package GroupStudy.November.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Fish {
    int row, col, dir;

    public Fish(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}

class Info {
    int smell;
    List<Fish> fishList;

    public Info(int smell, List<Fish> fishList) {
        this.smell = smell;
        this.fishList = fishList;
    }
}

class Shark {
    int row, col;

    public Shark(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Pos {
    int row, col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class boj_23290 {

    static Info[][] board = new Info[5][5];
    static int[] fishDrow = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 9시 부터 시작해서 시계방향 순
    static int[] fishDcol = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkDrow = {0, -1, 0, 1, 0};
    static int[] sharkDcol = {0, 0, -1, 0, 1};
    static int maxFishCount;
    static List<Pos> resultPaths;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());   // 물고기의 수
        int S = Integer.parseInt(st.nextToken());   // 마법연습 횟수

        // 물고기 초기화
        Queue<Fish> queue = new LinkedList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            queue.offer(new Fish(row, col, dir));
        }

        // 상어 초기화
        st = new StringTokenizer(br.readLine());
        Shark shark = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // board 초기화
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                board[i][j] = new Info(0, new ArrayList<>());
            }
        }

        // 시뮬레이션 진행
        while (S-- > 0) {
            // 1. 복제: 다시 큐에 넣어주는 방식으로 표현 + 2. 물고기 이동
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Fish current = queue.poll();
                int row = current.row;
                int col = current.col;
                int dir = current.dir;

                // 물고기의 이동 적용 후 board에 기록
                boolean canMove = false;
                for (int d = 1; d <= 8; d++) {
                    int nrow = row + fishDrow[dir];
                    int ncol = col + fishDcol[dir];

                    // 범위 안 + 물고기 냄새 X + 상어 X
                    if (!isOut(nrow, ncol) && board[nrow][ncol].smell == 0) {
                        if (shark.row != nrow || shark.col != ncol) {
                            board[nrow][ncol].fishList.add(new Fish(nrow, ncol, dir));
                            canMove = true;
                            break;
                        }
                    }

                    dir -= 1;
                    if (dir == 0) dir = 8;
                }

                // 8방 탐색을 했지만 이동할 곳이 없다면 그 자리에 멈춰있는다.
                if (!canMove) {
                    board[row][col].fishList.add(current);
                }

                // 다시 큐에 넣어줘서 복제 시켜준다. (size 만큼만 반복했기 때문에 다시 들어온 애들에게는 영향이 안간다.)
                queue.offer(current);
            }

            // 3. 최대로 먹을 수 있는 경로 탐색 후 처리
            maxFishCount = Integer.MIN_VALUE;
            resultPaths = new ArrayList<>();
            int[][] copy = new int[5][5];

            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    copy[i][j] = board[i][j].fishList.size();
                }
            }

            sharkMove(0, 0, new ArrayList<>(), shark, copy);
            for (Pos path : resultPaths) {
                shark.row = path.row;
                shark.col = path.col;

                if (board[shark.row][shark.col].fishList.size() >= 1) {
                    board[shark.row][shark.col].smell = 3;
                    board[shark.row][shark.col].fishList.clear();
                }

            }

            // 4. 두 번 전 연습에서 생긴 냄새가 사라진다.
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    if (board[i][j].smell >= 1)
                        board[i][j].smell--;
                }
            }

            // board에 있는 애들을 다시 큐에 담아준다.
            for (int row = 1; row <= 4; row++) {
                for (int col = 1; col <= 4; col++) {
                    if (board[row][col] != null) {
                        for (Fish fish : board[row][col].fishList) {
                            queue.offer(fish);
                        }
                        board[row][col].fishList.clear();
                    }
                }
            }
        }

        System.out.println(queue.size());
    }

    private static void sharkMove(int count, int total, List<Pos> paths, Shark shark, int[][] copy) {
        if (count == 3) {
            if (maxFishCount < total) {
                maxFishCount = total;
                resultPaths.clear();
                for (Pos path : paths) {
                    resultPaths.add(path);
                }
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            int nrow = shark.row + sharkDrow[i];
            int ncol = shark.col + sharkDcol[i];

            if (!isOut(nrow, ncol)) {
                paths.add(new Pos(nrow, ncol));
                int fishCount = copy[nrow][ncol];
                copy[nrow][ncol] = 0;
                sharkMove(count + 1, total + fishCount, paths, new Shark(nrow, ncol), copy);
                paths.remove(paths.size() - 1);
                // 경로가 안겹치는 경우에만 원래대로 복구시켜준다.
                boolean needRestore = true;
                for (Pos path : paths) {
                    if (path.row == nrow && path.col == ncol) {
                        needRestore = false;
                        break;
                    }
                }
                if (needRestore)
                    copy[nrow][ncol] = board[nrow][ncol].fishList.size();
            }
        }

        return;
    }

    private static boolean isOut(int row, int col) {
        if (row >= 1 && row <= 4 && col >= 1 && col <= 4) return false;
        return true;
    }
}

