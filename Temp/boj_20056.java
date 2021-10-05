//package GroupStudy;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//class Fireball {
//    int r, c, m, d, s;
//
//    public Fireball(int r, int c, int m, int d, int s) {
//        this.r = r;
//        this.c = c;
//        this.m = m;
//        this.d = d;
//        this.s = s;
//    }
//}
//
//class Info {
//    int totalMass, totalCount, totalSpeed;
//    int mode;   // 방향이 홀수인지 짝수인지 여부와 방향이 다 같은지 확인하기 위한 변수
//
//    public Info(int totalMass, int totalCount, int totalSpeed, int mode) {
//        this.totalMass = totalMass;
//        this.totalCount = totalCount;
//        this.totalSpeed = totalSpeed;
//        this.mode = mode;
//    }
//}
//
//public class boj_20056 {
//
//    static int N, M, K;
//    static int[] drow = {-1, -1, 0, 1, 1, 1, 0, -1};
//    static int[] dcol = {0, 1, 1, 1, 0, -1, -1, -1};
//    static Info[][] board;
//    static int[] sameDir = {0, 2, 4, 6};
//    static int[] diffDir = {1, 3, 5, 7};
//
//    public static void Main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//
//        board = new Info[N + 1][N + 1];
//        Queue<Fireball> queue = new LinkedList<>();
//
//        // 파이어볼 정보 입력받기 M번
//        while (M-- > 0) {
//            st = new StringTokenizer(br.readLine());
//            int r = Integer.parseInt(st.nextToken());   // 행
//            int c = Integer.parseInt(st.nextToken());   // 열
//            int m = Integer.parseInt(st.nextToken());   // 질량
//            int s = Integer.parseInt(st.nextToken());   // 속도
//            int d = Integer.parseInt(st.nextToken());   // 방향
//
//            Fireball fireball = new Fireball(r, c, m, d, s);
//            queue.offer(fireball);
//        }
//
//        simulation(queue, K);
//
//        // 남아있는 파이어볼 질량의 합
//        int answer = 0;
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                if (board[i][j] != null) {
//                    answer += board[i][j].totalMass;
//                }
//            }
//        }
//
//        System.out.println(answer);
//    }
//
//    public static void simulation(Queue<Fireball> queue, int loop) {
//        // fireball 이동
//        while (!queue.isEmpty()) {
//            Fireball current = queue.poll();
//            // 행과 열의 위치가 0이나 N+1에 위치하게 되면 순환되는 구조로 좌표 변경을 해줘야함.
//            // 첫 제출때 틀렸던 부분 ()
////            int nrow = current.r + drow[current.d] * (current.s % N);
////            int ncol = current.c + dcol[current.d] * (current.s % N);
//            int nrow = (current.r + drow[current.d] * current.s) % N;
//            int ncol = (current.c + dcol[current.d] * current.s) % N;
////            if (nrow < 1)
////                nrow = N - Math.abs(nrow);
////            else if (nrow > N)
////                nrow %= N;
////
////            if (ncol < 1)
////                ncol = N - Math.abs(ncol);
////            else if (ncol > N)
////                ncol %= N;
//
//            // board 정보 갱신
//            // 1. board가 null인 경우
//            if (board[nrow][ncol] == null) {
//                // current.d로 초기화하는 이유: 칸에 2개이상 파이어볼이 존재하게 되면 어차피 쪼개지기 때문에 의미 없음.
//                // 반면, 칸에 1개의 파이어볼만 존재하게 되면 그 파이어볼의 이동방향을 담아둬야 하기때문에 current.d로 담아둬야함.
//                board[nrow][ncol] = new Info(current.m, 1, current.s, current.d);
//            }
//            // 2. board가 null이 아닌 경우
//            else {
//                // 방향 확인 (홀수인지 짝수인지)
//                int dir = current.d % 2;
//                // 다르다면 mode를 -1로 바꿔준다.
//                if (dir != board[nrow][ncol].mode % 2)
//                    board[nrow][ncol].mode = -1;
//
//                board[nrow][ncol].totalCount++;
//                board[nrow][ncol].totalMass += current.m;
//                board[nrow][ncol].totalSpeed += current.s;
//            }
//        }
//
//        // 명령 K번 수행한 경우 종료
//        if (loop == 0)
//            return;
//
//        // 4개로 나누어진다.
//        Queue<Fireball> nextQueue = new LinkedList<>();
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                if (board[i][j] != null && board[i][j].totalCount >= 2) {
//                    int mass = (int) Math.floor(board[i][j].totalMass / 5);
//                    int speed = (int) Math.floor(board[i][j].totalSpeed / board[i][j].totalCount);
//                    if (mass > 0) {
//                        for (int k = 0; k < 4; k++) {
//                            // 홀수거나 짝수로만 이루어진 경우
//                            Fireball nFireball;
//                            if (board[i][j].mode >= 0)
//                                nFireball = new Fireball(i, j, mass, sameDir[k], speed);
//                            else
//                                nFireball = new Fireball(i, j, mass, diffDir[k], speed);
//
//                            nextQueue.offer(nFireball);
//                        }
//                    }
//                } else if (board[i][j] != null && board[i][j].totalCount == 1) {
//                    Fireball nFireball = new Fireball(i, j, board[i][j].totalMass, board[i][j].mode, board[i][j].totalSpeed);
//                    nextQueue.offer(nFireball);
//                }
//                // 4개로 분열되거나 이동하게 되면 원래 있던 칸은 null로 변경해준다.
//                board[i][j] = null;
//            }
//        }
//
//        if (!nextQueue.isEmpty()) {
//            simulation(nextQueue, loop - 1);
//        }
//    }
//}
