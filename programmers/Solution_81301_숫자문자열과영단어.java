package programmers;

public class Solution_81301_숫자문자열과영단어 {
    public int solution(String s) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < words.length; i++) {
            s = s.replaceAll(words[i], String.valueOf(i));
        }
        return Integer.parseInt(s);
    }
}
