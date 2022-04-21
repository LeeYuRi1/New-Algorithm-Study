package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
    private static int N, L, R, area[][];
    private static boolean move, visited[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        area = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move = true;
        int ans = -1;
        while (move) { // 인구이동이 발생하는 동안 반복
            visited = new boolean[N][N];
            move = false;
            ans++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> moveQueue = new LinkedList<>(); // 연합 국가들
        queue.offer(new int[]{x, y});
        moveQueue.offer(new int[]{x, y});
        visited[x][y] = true;
        int sum = area[x][y];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
                int diff = Math.abs(area[cur[0]][cur[1]] - area[nx][ny]);
                if (diff < L || diff > R) continue;
                queue.offer(new int[]{nx, ny});
                moveQueue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                sum += area[nx][ny];
            }
        }
        if (moveQueue.size() == 1) return;
        move = true; // 인구이동 발생
        int r = sum / moveQueue.size(); //  각 칸의 인구수 = (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
        while (!moveQueue.isEmpty()) {
            int[] cur = moveQueue.poll();
            area[cur[0]][cur[1]] = r;
        }
    }
}
