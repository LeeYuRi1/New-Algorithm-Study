package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1,863 ms, 95,732 kb
public class Solution_5643_키순서 {
    private static boolean[] visited;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            List<Integer>[] adjUp = new ArrayList[N + 1];
            List<Integer>[] adjDown = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adjUp[i] = new ArrayList<>();
                adjDown[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjUp[a].add(b);
                adjDown[b].add(a);
            }
            int ans = 0;
            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                count = 0;
                dfs(adjUp, i);
                dfs(adjDown, i);
                if (count == N - 1) ans++;
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(List<Integer>[] adj, int cur) {
        visited[cur] = true;
        for (int next : adj[cur]) {
            if (!visited[next]) {
                count++;
                dfs(adj, next);
            }
        }
    }
}
