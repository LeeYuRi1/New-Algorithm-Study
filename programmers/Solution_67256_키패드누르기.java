package programmers;

import java.util.HashMap;

public class Solution_67256_키패드누르기 {
    private HashMap<Integer, int[]> map = new HashMap<>();
    private int[] left = {3, 0};
    private int[] right = {3, 2};

    public String solution(int[] numbers, String hand) {
        for (int i = 0, num = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map.put(num++, new int[]{i, j});
            }
        }
        map.put(0, new int[]{3, 1});

        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
                left = map.get(num);
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
                right = map.get(num);
            } else {
                sb.append(getHand(num, hand));
            }
        }
        return sb.toString();
    }

    private String getHand(int num, String hand) {
        int[] cur = map.get(num);
        int distL = Math.abs(left[0] - cur[0]) + Math.abs(left[1] - cur[1]);
        int distR = Math.abs(right[0] - cur[0]) + Math.abs(right[1] - cur[1]);
        if ((distL == distR && hand.equals("left")) || distL < distR) {
            left = cur;
            return "L";
        } else {
            right = cur;
            return "R";
        }
    }
}
