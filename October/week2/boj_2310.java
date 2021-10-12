package GroupStudy.October.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Room {
    char type;
    int cost;
    List<Integer> nextRooms;

    public Room(char type, int cost, List<Integer> nextRooms) {
        this.type = type;
        this.cost = cost;
        this.nextRooms = nextRooms;
    }
}

public class boj_2310 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            Room[] rooms = new Room[n + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                char type = st.nextToken().charAt(0);
                int cost = Integer.parseInt(st.nextToken());
                List<Integer> nextRooms = new ArrayList<>();
                while (true) {
                    int nextRoomNo = Integer.parseInt(st.nextToken());
                    if (nextRoomNo == 0) break;
                    nextRooms.add(nextRoomNo);
                }

                rooms[i] = new Room(type, cost, nextRooms);
            }

            bfs(rooms, n);
        }

        System.out.println(sb);
    }

    private static void bfs(Room[] rooms, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[rooms.length + 1];

        queue.offer(1);
        visited[1] = true;

        int money = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            if(current == n) {
                sb.append("Yes").append("\n");
                return;
            }

            char type = rooms[current].type;
            int cost = rooms[current].cost;
            List<Integer> nextRooms = rooms[current].nextRooms;

            for (Integer roomNo : nextRooms) {
                if (!visited[roomNo]) {
                    if (rooms[roomNo].type == 'L') {
                        if (money < rooms[roomNo].cost) {
                            money = rooms[roomNo].cost;
                        }
                        queue.offer(roomNo);
                        visited[roomNo] = true;
                    } else if (rooms[roomNo].type == 'T') {
                        if (money - rooms[roomNo].cost >= 0) {
                            money -= rooms[roomNo].cost;
                            queue.offer(roomNo);
                            visited[roomNo] = true;
                        }
                    }
                }
            }
        }

        sb.append("No").append("\n");
    }
}
