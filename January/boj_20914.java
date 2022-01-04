package GroupStudy.January;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Pos {
    int row, col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class boj_20914 {
    static Map<Character, Pos> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initKeyboard(map);
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            int time = 1;
            Pos current = map.get(input[0]);

            for (int j = 1; j < input.length; j++) {
                // 다음 버튼과 현재 버튼 사이의 최단 시간 계산
                Pos next = map.get(input[j]);
                int rowDistance = Math.abs(current.row - next.row);
                int colDistance = (Math.abs(current.col - next.col) - rowDistance/2);
                colDistance = colDistance <= 0 ? 0 : colDistance;
                time += rowDistance + colDistance;
                // 버튼 누르기
                time++;
                // 버튼 갱신
                current = next;
            }
            System.out.println(time);
        }
    }

    private static void initKeyboard(Map<Character, Pos> map) {
        map.put('Q', new Pos(0, 0));
        map.put('W', new Pos(0, 2));
        map.put('E', new Pos(0, 4));
        map.put('R', new Pos(0, 6));
        map.put('T', new Pos(0, 8));
        map.put('Y', new Pos(0, 10));
        map.put('U', new Pos(0, 12));
        map.put('I', new Pos(0, 14));
        map.put('O', new Pos(0, 16));
        map.put('P', new Pos(0, 18));
        map.put('A', new Pos(2, 1));
        map.put('S', new Pos(2, 3));
        map.put('D', new Pos(2, 5));
        map.put('F', new Pos(2, 7));
        map.put('G', new Pos(2, 9));
        map.put('H', new Pos(2, 11));
        map.put('J', new Pos(2, 13));
        map.put('K', new Pos(2, 15));
        map.put('L', new Pos(2, 17));
        map.put('Z', new Pos(4, 2));
        map.put('X', new Pos(4, 4));
        map.put('C', new Pos(4, 6));
        map.put('V', new Pos(4, 8));
        map.put('B', new Pos(4, 10));
        map.put('N', new Pos(4, 12));
        map.put('M', new Pos(4, 14));
    }
}
