package GroupStudy;

public class 문자열압축 {
    public static int solution(String s) {
        int answer = 0;

        // 1개 부터 n개 단위까지 나눠보고 거기서 최소를 결과로 반환
        int length = s.length();
        answer = length;

        for (int len = 1; len <= length; len++) {
            String pattern = "";
            StringBuilder sb = new StringBuilder();
            int index = 0;
            int count = 1;
            while (index + len <= length) {
                String nextPattern = s.substring(index, index + len);
                if (index == 0) {
                    pattern = nextPattern;
                } else {
                    // 패턴이 일치하는 경우
                    if (pattern.equals(nextPattern)) {
                        count++;
                    }

                    // 패턴이 다른 경우
                    else {
                        if (count >= 2) sb.append(count);
                        sb.append(pattern);
                        pattern = nextPattern;
                        count = 1;
                    }
                }
                index += len;
            }

            // 끝났으면 마지막에 pattern 삽입
            if (count >= 2) {
                sb.append(count);
            }
            sb.append(pattern);
            // 나머지 처리
            sb.append(s.substring(index));
            // 최소 길이 갱신
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("abcabcabcabcdededededede"));
    }
}
