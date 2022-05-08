package programmers;

import java.util.PriorityQueue;

public class Solution_42626_더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.offer(i);
        }
        while (pq.peek() < K && pq.size() > 1) {
            int n1 = pq.poll();
            int n2 = pq.poll();
            pq.offer(n1 + (n2 * 2));
            answer++;
        }
        return pq.peek() < K ? -1 : answer;
    }
}
