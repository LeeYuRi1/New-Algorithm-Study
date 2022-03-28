package programmers;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_92334_신고결과받기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(id_list[i], i); // 각 유저의 인덱스
        }

        boolean[][] reportArr = new boolean[n][n]; // row: 신고한사람 col: 신고당한사람
        for (String s : report) {
            String[] str = s.split(" ");
            int i = map.get(str[0]);
            int j = map.get(str[1]);
            reportArr[i][j] = true;
        }

        boolean[] check = new boolean[n]; // k번 이상 신고당하면 정지(true)
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (reportArr[i][j]) count++;
            }
            if (count >= k) check[j] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (reportArr[i][j] && check[j]) answer[i]++; // 자신이 신고한 사람이 정지당한 사람이면 더해줌
            }
        }
        return answer;
    }
}
