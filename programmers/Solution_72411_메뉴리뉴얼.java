package programmers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Solution_72411_메뉴리뉴얼 {
    private Map<String, Integer> map;
    private boolean[] check;

    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        check = new boolean[11];
        for (int i : course) {
            check[i] = true;
        }

        for (String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            subset(arr, new boolean[arr.length], 0);
        }

        List<String> answer = new ArrayList<>();
        int[] max = new int[11];
        map.forEach((key, value) -> {
            int i = key.length();
            max[i] = Math.max(max[i], value);
        });
        map.forEach((key, value) -> {
            if (key.length() >= 2 && value >= 2 && max[key.length()] == value) answer.add(key);
        });
        Collections.sort(answer);

        return answer.toArray(new String[0]);
    }

    private void subset(char[] arr, boolean[] flag, int cnt) {
        if (cnt == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (flag[i]) sb.append(arr[i]);
            }
            String str = sb.toString();
            if (check[str.length()]) map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }
        flag[cnt] = true;
        subset(arr, flag, cnt + 1);
        flag[cnt] = false;
        subset(arr, flag, cnt + 1);
    }
}
