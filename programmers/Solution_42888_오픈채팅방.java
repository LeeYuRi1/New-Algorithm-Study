package programmers;

import java.util.HashMap;

public class Solution_42888_오픈채팅방 {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        int count = 0;
        for (String s : record) {
            String[] str = s.split(" ");
            if (!str[0].equals("Leave")) map.put(str[1], str[2]);
            if (!str[0].equals("Change")) count++;
        }
        String[] answer = new String[count];
        int i = 0;
        for (String s : record) {
            String[] str = s.split(" ");
            if (str[0].equals("Enter")) answer[i++] = map.get(str[1]) + "님이 들어왔습니다.";
            if (str[0].equals("Leave")) answer[i++] = map.get(str[1]) + "님이 나갔습니다.";
        }
        return answer;
    }
}