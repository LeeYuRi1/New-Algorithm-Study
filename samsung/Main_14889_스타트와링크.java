package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
    private static int N, min = Integer.MAX_VALUE, arr[][];
    private static boolean[] teamCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        teamCheck = new boolean[N];
        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int cnt, int start) {
        if (cnt == N / 2) {
            int s1 = getScore(true);
            int s2 = getScore(false);
            min = Math.min(min, Math.abs(s1 - s2));
            return;
        }
        for (int i = start; i < N; i++) {
            teamCheck[i] = true;
            dfs(cnt + 1, i + 1);
            teamCheck[i] = false;
        }
    }

    private static int getScore(boolean tn) {
        int[] team = new int[N / 2];
        for (int i = 0, j = 0; i < teamCheck.length; i++) {
            if (teamCheck[i] == tn) team[j++] = i;
        }
        int score = 0;
        for (int i : team) {
            for (int j : team) {
                score += arr[i][j];
            }
        }
        return score;
    }
}
