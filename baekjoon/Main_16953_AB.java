package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16953_AB {
    private static class Number {
        long num;
        int count;

        public Number(long num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        System.out.println(bfs(A, B));
    }

    private static int bfs(int A, int B) {
        Queue<Number> queue = new LinkedList<>();
        queue.offer(new Number(A, 1));
        while (!queue.isEmpty()) {
            Number cur = queue.poll();
            if (cur.num == B) return cur.count;
            long[] next = {cur.num * 2, cur.num * 10 + 1};
            for (int i = 0; i < 2; i++) {
                if (next[i] >= 1000000000) continue;
                queue.offer(new Number(next[i], cur.count + 1));
            }
        }
        return -1;
    }
}
