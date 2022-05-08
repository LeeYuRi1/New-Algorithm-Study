package programmers;

import java.util.HashSet;
import java.util.Set;

public class Solution_1845_폰켓몬 {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        return set.size() < nums.length / 2 ? set.size() : nums.length / 2;
    }
}
