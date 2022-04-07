package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 130 ms, 24,588 kb
public class Solution_1953_탈주범검거 {
    private static int N, M, R, C, L, map[][];
    private static int[] dx = {-1, 0, 1, 0}; // 상우하좌
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] tunnel = {{}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {0, 1}, {1, 2}, {2, 3}, {0, 3}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[N][M];
        queue.offer(new int[]{R, C});
        dist[R][C] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int num = map[cur[0]][cur[1]];
            for (int tn : tunnel[num]) {
                int nx = cur[0] + dx[tn];
                int ny = cur[1] + dy[tn];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (dist[nx][ny] > 0 || map[nx][ny] == 0) continue;
                // 현재위치의 터널 방향이랑 다음위치의 터널 방향이랑 이어지는지 확인
                for (int t : tunnel[map[nx][ny]]) {
                    if (t == (tn + 2) % 4) {
                        queue.offer(new int[]{nx, ny});
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }
        return find(dist);
    }

    private static int find(int[][] dist) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[i][j] != 0 && dist[i][j] <= L) count++;
            }
        }
        return count;
    }
}
