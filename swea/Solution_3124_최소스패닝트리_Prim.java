package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 소요 시간 : 4,593 ms
// 메모리 사용량 : 174,720 kb
class Edge_3124_1 implements Comparable<Edge_3124_1> {
    int to;
    int w;

    Edge_3124_1(int to, int w) {
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge_3124_1 o) {
        return Integer.compare(this.w, o.w);
    }
}

public class Solution_3124_최소스패닝트리_Prim {
    private static int V, E;
    private static List<Edge_3124_1>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                adjList[A].add(new Edge_3124_1(B, C));
                adjList[B].add(new Edge_3124_1(A, C));
            }
            sb.append("#").append(tc).append(" ").append(prim()).append("\n");
        }
        System.out.println(sb);
    }

    private static long prim() {
        PriorityQueue<Edge_3124_1> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];
        pq.offer(new Edge_3124_1(1, 0));
        long sum = 0;
        while (!pq.isEmpty()) {
            Edge_3124_1 cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            sum += cur.w;
            for (Edge_3124_1 next : adjList[cur.to]) {
                pq.offer(next);
            }
        }
        return sum;
    }
}
