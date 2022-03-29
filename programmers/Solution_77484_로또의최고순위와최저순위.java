package programmers;

import java.util.HashSet;

public class Solution_77484_로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : win_nums) {
            set.add(i);
        }
        int min = 0, zero = 0;
        for (int i : lottos) {
            if (set.contains(i)) min++;
            if (i == 0) zero++;
        }
        int max = min + zero;
        if (max == 0) max++;
        if (min == 0) min++;
        return new int[]{7 - max, 7 - min};
    }
}
