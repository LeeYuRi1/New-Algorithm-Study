package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
    private static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    private static int[] num;
    private static boolean[] visited;
    private static List<Integer> opList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        opList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(st.nextToken());
            while (temp-- > 0) opList.add(i);
        }
        visited = new boolean[N - 1];
        dfs(0, num[0]);
        System.out.println(max + "\n" + min);
    }

    private static void dfs(int cnt, int result) {
        if (cnt == N - 1) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }
        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(cnt + 1, cal(result, num[cnt + 1], opList.get(i)));
                visited[i] = false;
            }
        }
    }

    private static int cal(int cur, int next, int op) {
        if (op == 0) return cur + next;
        else if (op == 1) return cur - next;
        else if (op == 2) return cur * next;
        return cur / next;
    }
}
