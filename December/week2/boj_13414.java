package GroupStudy.December.week2;

import java.io.*;
import java.util.*;

public class boj_13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());   // 수강 가능 인원
        int L = Integer.parseInt(st.nextToken());   // 대기 목록 길이

        Map<String, Integer> idMap = new HashMap<>();
        String[] students = new String[L];
        for (int i = 0; i < L; i++) {
            String studentId = br.readLine();
            students[i] = studentId;
            if(idMap.containsKey(studentId)) {
                students[idMap.get(studentId)] = null;
            }
            idMap.put(studentId, i);
        }

        int count = 0;
        for (String student : students) {
            if(count == K) break;
            if(student != null) {
                sb.append(student).append("\n");
                count++;
            }
        }

        System.out.print(sb.toString());
    }
}
