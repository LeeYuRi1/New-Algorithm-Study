package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
    private static int N, H, ans;
    private static int[][] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선
        int M = Integer.parseInt(st.nextToken()); // 가로선
        H = Integer.parseInt(st.nextToken()); // 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수
        line = new int[H][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            line[a][b] = 1; // 오른쪽에 선이 있음
            line[a][b + 1] = -1; // 왼쪽에 선이 있음
        }
        ans = -1;
        for (int i = 0; i <= 3; i++) {
            dfs(0, i);
            if (ans != -1) break;
        }
        System.out.println(ans);
    }

    private static void dfs(int cnt, int n) {
        if (ans != -1) return;
        if (cnt == n) {
            if (isSuccess()) ans = n;
            return;
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (line[i][j] == 0 && line[i][j + 1] == 0) { // 양쪽 다 이어진 선이 없어야함
                    line[i][j] = 1;
                    line[i][j + 1] = -1;
                    dfs(cnt + 1, n);
                    line[i][j] = line[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean isSuccess() {
        for (int j = 0; j < N; j++) {
            int cur = j;
            for (int i = 0; i < H; i++) {
                if (line[i][cur] == 1) cur++;
                else if (line[i][cur] == -1) cur--;
            }
            if (cur != j) return false;
        }
        return true;
    }
}