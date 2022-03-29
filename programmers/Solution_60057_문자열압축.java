package programmers;

public class Solution_60057_문자열압축 {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder result = new StringBuilder();
            String repeat = "";
            int count = 1;
            for (int j = 0; j < s.length(); j += i) {
                String str = i + j > s.length() ? s.substring(j) : s.substring(j, j + i);
                if (str.equals(repeat)) {
                    count++;
                } else {
                    result.append(count > 1 ? count + repeat : repeat);
                    repeat = str;
                    count = 1;
                }
            }
            result.append(count > 1 ? count + repeat : repeat);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}
