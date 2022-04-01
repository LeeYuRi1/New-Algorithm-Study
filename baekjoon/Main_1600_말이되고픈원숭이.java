package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 452ms, 87748KB
public class Main_1600_말이되고픈원숭이 {
    private static int K, W, H, area[][];
    private static boolean[][][] visited;
    private static Queue<Point> queue = new LinkedList<>();
    private static int[] mx = {-1, 1, 0, 0};
    private static int[] my = {0, 0, -1, 1};
    private static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static class Point {
        int x, y, k, count;

        Point(int x, int y, int k, int count) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        area = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        visited = new boolean[H][W][K + 1]; // 경로 겹침 -> 3차원 배열(x,y 위치에서 k회 남음)
        queue.offer(new Point(0, 0, K, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == H - 1 && cur.y == W - 1) return cur.count;
            if (cur.k > 0) move(hx, hy, cur, cur.k - 1); // k가 0보다 크면 말 이동
            move(mx, my, cur, cur.k); // else 붙이기 말기! 원숭이 이동
        }
        return -1;
    }

    private static void move(int[] dx, int[] dy, Point cur, int nk) {
        for (int i = 0; i < dx.length; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
            if (visited[nx][ny][nk] || area[nx][ny] == 1) continue;
            queue.offer(new Point(nx, ny, nk, cur.count + 1));
            visited[nx][ny][nk] = true;
        }
    }
}
