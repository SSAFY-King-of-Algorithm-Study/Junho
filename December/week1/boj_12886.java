package GroupStudy.December.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * -기저조건1: 돌의 합이 3의 배수가 아니면 불가능
 * -기저조건2: 시작부터 다 같다면 바로 종료가능
 * 2개의 돌의 개수를 알면 나머지 하나의 개수도 바로 알 수 있는 특성을 활용
 * ==> 이러면 굳이 500*500*500짜리 visited 배열을 사용할 필요가없음.
 * 단, 이미 했던 조합인 경우는 무시해줘서 무한 루프를 피한다.
 */

class Stones {
    int groupA, groupB, groupC;

    public Stones(int groupA, int groupB, int groupC) {
        this.groupA = groupA;
        this.groupB = groupB;
        this.groupC = groupC;
    }
}

public class boj_12886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        if (baseCase(A, B, C)) return;

        Stones stones = new Stones(A, B, C);
        boolean[][] visited = new boolean[1501][1501];

        // BFS
        Queue<Stones> queue = new LinkedList<>();
        queue.offer(stones);

        while (!queue.isEmpty()) {
            Stones current = queue.poll();
            int currentA = current.groupA;
            int currentB = current.groupB;
            int currentC = current.groupC;

            // 숫자가 다 같다면
            if (isAllSame(currentA, currentB, currentC)) return;

            // 개수가 다른 두 그룹끼리 연산 진행 (X+X , Y-X)
            if (currentA != currentB && !visited[currentA][currentB] && !visited[currentB][currentA]) {
                int nextA = currentA < currentB ? currentA * 2 : currentA - currentB;
                int nextB = currentA > currentB ? currentB * 2 : currentB - currentA;
                visited[currentA][currentB] = true;
                visited[currentB][currentA] = true;
                queue.offer(new Stones(nextA, nextB, currentC));
            }
            if (currentA != currentC && !visited[currentA][currentC] && !visited[currentC][currentA]) {
                int nextA = currentA < currentC ? currentA * 2 : currentA - currentC;
                int nextC = currentA > currentC ? currentC * 2 : currentC - currentA;
                visited[currentA][currentC] = true;
                visited[currentC][currentA] = true;
                queue.offer(new Stones(nextA, currentB, nextC));
            }
            if (currentB != currentC && !visited[currentB][currentC] && !visited[currentC][currentB]) {
                int nextB = currentB < currentC ? currentB * 2 : currentB - currentC;
                int nextC = currentB > currentC ? currentC * 2 : currentC - currentB;
                visited[currentB][currentC] = true;
                visited[currentC][currentB] = true;
                queue.offer(new Stones(currentA, nextB, nextC));
            }
        }

        System.out.println(0);
    }

    private static boolean baseCase(int A, int B, int C) {
        // 기저조건1
        if ((A + B + C) % 3 != 0) {
            System.out.println(0);
            return true;
        }

        // 기저조건2
        if (isAllSame(A, B, C)) return true;

        return false;
    }

    private static boolean isAllSame(int A, int B, int C) {
        if (A == B && B == C) {
            System.out.println(1);
            return true;
        }
        return false;
    }

}
