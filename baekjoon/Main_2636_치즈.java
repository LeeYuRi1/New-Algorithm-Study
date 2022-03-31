package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 108ms, 13980KB
public class Main_2636_치즈 {
    private static int n, m, area[][];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num, ans = 0, ansCount = 0;
        while ((num = bfs()) > 0) {
            ansCount++;
            ans = num;
        }
        System.out.println(ansCount + "\n" + ans);
    }

    // 겉부분만 녹아야함 (0,0)에서 시작해서 치즈(1)나오면 0으로 바꾸고 큐에 넣지 않음
    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if (area[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                } else {
                    area[nx][ny] = 0;
                    count++;
                }
            }
        }
        return count;
    }
}
