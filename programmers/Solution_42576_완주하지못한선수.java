package programmers;

import java.util.HashMap;

public class Solution_42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        // 완주한 선수와 횟수(동명이인)를 map에 넣음
        for (String i : completion) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // 참여한 선수들 중 map에 없거나 횟수가 0이면 완주하지 못한 선수
        for (String i : participant) {
            if (!map.containsKey(i) || map.get(i) == 0) return i;
            else map.put(i, map.get(i) - 1);
        }
        return "";
    }
}
