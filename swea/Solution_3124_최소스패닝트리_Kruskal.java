package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 소요 시간 : 1,667 ms
// 메모리 사용량 : 118,068 kb
class Edge_3124_2 implements Comparable<Edge_3124_2> {
    int from;
    int to;
    int w;

    Edge_3124_2(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_3124_2 o) {
        return Integer.compare(this.w, o.w);
    }
}

public class Solution_3124_최소스패닝트리_Kruskal {
    private static int V, E;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            List<Edge_3124_2> list = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                list.add(new Edge_3124_2(A, B, C));
            }
            Collections.sort(list);
            makeSet();
            long ans = 0, count = 0;
            for (Edge_3124_2 e : list) {
                if (union(e.from, e.to)) {
                    ans += e.w;
                    if (++count == V - 1) break;
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeSet() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}
