package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 135 ms, 22,308 kb
public class Solution_1249_보급로 {
    private static int N, map[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static class Point implements Comparable<Point> {
        int x, y, cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Character.getNumericValue(input.charAt(j));
                }
            }
            sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.x == N - 1 && cur.y == N - 1) return cur.cost;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                pq.offer(new Point(nx, ny, cur.cost + map[nx][ny]));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}
