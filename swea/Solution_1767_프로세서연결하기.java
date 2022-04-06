package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 396 ms, 25,368 kb
public class Solution_1767_프로세서연결하기 {
    private static int N, max, min, area[][];
    private static List<int[]> list;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            area = new int[N][N];
            list = new ArrayList<>(); // 가장자리가 아닌 코어리스트
            max = 0; // 최대 연결 코어수
            min = Integer.MAX_VALUE; // 최소 전선길이의 합
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    area[i][j] = Integer.parseInt(st.nextToken());
                    if ((i == 0 || i == N - 1 || j == 0 || j == N - 1) && area[i][j] == 1) continue; // 가장자리 코어는 넘어감
                    if (area[i][j] == 1) list.add(new int[]{i, j});
                }
            }
            go(0, 0);
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static void go(int index, int cCnt) { // 부분집합으로 코어 전선놓기 시도, cCnt: 전원과 연결된 코어수
        if (index == list.size()) {
            int res = getLength();
            if (max < cCnt) {
                max = cCnt;
                min = res;
            } else if (max == cCnt) { // 최대 연결 코어수가 같다면
                min = Math.min(min, res);
            }
            return;
        }
        int[] core = list.get(index);
        int x = core[0];
        int y = core[1];
        // 전선을 놓아보기(4방향으로)
        for (int d = 0; d < 4; d++) {
            if (isAvailable(x, y, d)) { // 현재 코어의 x,y 위치에서 d방향으로 전선을 놓을 수 있다면
                setStatus(x, y, d, 2); // 전선 놓기
                go(index + 1, cCnt + 1);
                setStatus(x, y, d, 0);
            }
        }
        // 전선 놓지 않기
        go(index + 1, cCnt);
    }

    private static boolean isAvailable(int x, int y, int d) {
        int nx = x, ny = y;
        while (true) {
            nx += dx[d];
            ny += dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
            if (area[nx][ny] >= 1) return false; // 다른 코어나 전선을 만나면 return false;
        }
        return true;
    }

    private static void setStatus(int x, int y, int d, int s) { // x,y 위치에서 d방향으로 전선을 놓거나(2) 지우거나(0)
        int nx = x, ny = y;
        while (true) {
            nx += dx[d];
            ny += dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
            area[nx][ny] = s;
        }
    }

    private static int getLength() { // 놓아진 전선의 길이의 합 계산
        int lCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] == 2) lCnt++;
            }
        }
        return lCnt;
    }
}
