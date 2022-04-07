package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {
    private static int N, M, area[][];
    private static int[] dx = {-1, 0, 1, 0}; // 북동남서
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        area = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(clean(r, c, d, 0));
    }

    private static int clean(int r, int c, int d, int count) {
        if (area[r][c] == 0) {
            count++;
            area[r][c] = 2;
        }
        boolean go = false;
        int nx = 0, ny = 0;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 왼쪽 방향으로 회전
            nx = r + dx[d];
            ny = c + dy[d];
            if (area[nx][ny] == 0) { // 왼쪽으로 전진할 수 있음
                go = true;
                break;
            }
        }
        if (!go) { // 4방향 다 가지 못하는 경우
            int nd = (d + 2) % 4; // 방향 유지하고 후진하므로 d를 바꾸지않음
            nx = r + dx[nd];
            ny = c + dy[nd];
            if (area[nx][ny] == 1) return count; // 뒤쪽이 벽이면 멈춤
        }
        return clean(nx, ny, d, count);
    }
}
