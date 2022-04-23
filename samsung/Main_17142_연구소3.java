package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17142_연구소3 {
    private static int N, M, zero, ans, area[][];
    private static List<Point> virusList;
    private static Point[] peekList;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static class Point {
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 연구소 크기
        M = Integer.parseInt(st.nextToken()); // 바이러스 개수
        area = new int[N][N];
        virusList = new ArrayList<>();
        zero = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken()); // 0: 빈칸, 1: 벽, 2: 바이러스
                if (area[i][j] == 0) zero++;
                if (area[i][j] == 2) virusList.add(new Point(i, j, 0));
            }
        }
        peekList = new Point[M];
        ans = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(int cnt, int start) {
        if (cnt == M) {
            int min = bfs();
            if (min != -1) ans = Math.min(ans, min);
            return;
        }
        for (int i = start; i < virusList.size(); i++) {
            peekList[cnt] = virusList.get(i);
            dfs(cnt + 1, i + 1);
        }
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int count = 0, max = 0;
        for (Point p : peekList) {
            queue.offer(p);
            visited[p.x][p.y] = true;
        }
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny] || area[nx][ny] == 1) continue; // 방문했거나 벽이면 넘어감
                if (area[nx][ny] == 0) { // 빈칸이면 값 업데이트
                    count++;
                    max = cur.time + 1;
                }
                queue.offer(new Point(nx, ny, cur.time + 1));
                visited[nx][ny] = true;
            }
        }
        return count == zero ? max : -1; // 모든 빈칸에 바이러스를 퍼뜨리지 못했으면 -1
    }
}
