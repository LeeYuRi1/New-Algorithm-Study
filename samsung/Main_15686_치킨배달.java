package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
    private static int M, ans, arr[];
    private static List<Point> houseList;
    private static List<Point> chickenList;

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) houseList.add(new Point(i, j));
                if (num == 2) chickenList.add(new Point(i, j));
            }
        }
        ans = Integer.MAX_VALUE;
        arr = new int[M];
        dfs(0, 0);
        System.out.println(ans);
    }

    // 치킨집 M개 고르기
    private static void dfs(int cnt, int start) {
        if (cnt == M) {
            ans = Math.min(ans, getDist());
            return;
        }
        for (int i = start; i < chickenList.size(); i++) {
            arr[cnt] = i;
            dfs(cnt + 1, i + 1);
        }
    }

    private static int getDist() {
        int result = 0; // 모든 집의 치킨거리 합
        for (Point house : houseList) { // 집마다 가장 가까운 치킨집과의 거리를 구함
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                Point chicken = chickenList.get(arr[i]);
                int dist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                min = Math.min(min, dist);
            }
            result += min;
        }
        return result;
    }
}
