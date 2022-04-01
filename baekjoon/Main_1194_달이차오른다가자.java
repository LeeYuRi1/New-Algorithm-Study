package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 116ms, 14416KB
public class Main_1194_달이차오른다가자 {
    private static int N, M, start[];
    private static char[][] area;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static class Point {
        int x, y;
        int key; // 키 값 여부
        int dist; // 움직인 거리

        Point(int x, int y, int key, int dist) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new char[N][];
        for (int i = 0; i < N; i++) {
            area[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (area[i][j] == '0') start = new int[]{i, j};
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][1 << 6];  // 3차원 - [x][y][가지고있는 열쇠]
        queue.offer(new Point(start[0], start[1], 0, 0));
        visited[start[0]][start[1]][0] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (area[cur.x][cur.y] == '1') return cur.dist;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (area[nx][ny] == '#' || visited[nx][ny][cur.key]) continue; // 벽이거나 이미 방문했으면 넘어감
                if (Character.isLowerCase(area[nx][ny])) { // 열쇠(소문자) 만나는 경우
                    int nk = cur.key | (1 << (area[nx][ny] - 'a')); // 열쇠를 추가
                    queue.offer(new Point(nx, ny, nk, cur.dist + 1));
                    visited[nx][ny][nk] = true;
                } else if (Character.isUpperCase(area[nx][ny])) { // 문(대문자) 만나는 경우
                    int nk = cur.key & (1 << (area[nx][ny] - 'A')); // 열쇠 포함 여부
                    if (nk > 0) { // 열쇠가 있다면 갈 수 있음
                        queue.offer(new Point(nx, ny, cur.key, cur.dist + 1));
                        visited[nx][ny][cur.key] = true;
                    }
                } else { // 그냥 갈 수있는 경우
                    queue.offer(new Point(nx, ny, cur.key, cur.dist + 1));
                    visited[nx][ny][cur.key] = true;
                }
            }
        }
        return -1;
    }
}
