package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14502_연구소 {
    private static int N, M, min = Integer.MAX_VALUE, map[][];
    private static List<int[]> virus;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int zero = 0; // 원래 빈 칸의 개수
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zero++;
                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }
        dfs(0);
        System.out.println(zero - min - 3); // 남은 빈칸 = 원래 빈칸 - (바이러스가 퍼진 칸 - 벽 세운 3칸)
    }

    private static void dfs(int cnt) {
        if (cnt == 3) {
            min = Math.min(bfs(), min); // 최대한 덜 퍼져야하므로 최소값
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int[] v : virus) {
            queue.offer(v);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny] || map[nx][ny] != 0) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }
        return count;
    }
}
