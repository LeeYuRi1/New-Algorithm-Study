package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 472ms, 23944KB
public class Main_17143_낚시왕 {
    private static int R, C, ans;
    private static Shark[][] area;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};

    private static class Shark {
        int s, d, z; // 속력, 이동방향, 크기

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        area = new Shark[R][C];
        for (int i = 0; i < M; i++) { // 상어 정보 입력
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            area[r][c] = new Shark(s, d, z);
        }
        for (int i = 0; i < C; i++) { // 1. 낚시왕 이동
            catchShark(i); // 2. 땅과 제일 가까운 상어 잡기
            moveSharks(); // 3. 상어 이동
        }
        System.out.println(ans);
    }

    private static void catchShark(int j) {
        for (int i = 0; i < R; i++) {
            if (area[i][j] != null) {
                ans += area[i][j].z;
                area[i][j] = null;
                return;
            }
        }
    }

    private static void moveSharks() {
        Shark[][] temp = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (area[i][j] == null) continue;
                Shark cur = area[i][j];
                int nr = i;
                int nc = j;
                int dist = cur.d < 2 ? cur.s % ((R - 1) * 2) : cur.s % ((C - 1) * 2);
                while (dist-- > 0) {
                    nr += dr[cur.d];
                    nc += dc[cur.d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                        cur.d += cur.d % 2 == 0 ? 1 : -1;
                        nr += dr[cur.d] * 2;
                        nc += dc[cur.d] * 2;
                    }
                }
                if (temp[nr][nc] == null || temp[nr][nc].z < cur.z) temp[nr][nc] = cur;
            }
        }
        area = temp;
    }
}
