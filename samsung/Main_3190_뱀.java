package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_3190_뱀 {
    private static int N, board[][], dir[];
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        int K = Integer.parseInt(br.readLine()); // 사과의 개수
        StringTokenizer st;
        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine()); // 사과의 위치
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = 1;
        }
        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        dir = new int[10001];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine()); // 뱀의 방향 변환 정보
            int x = Integer.parseInt(st.nextToken());
            int c = st.nextToken().charAt(0) == 'L' ? 3 : 1;
            dir[x] = c;
        }
        System.out.println(play());
    }

    private static int play() {
        Deque<int[]> deque = new ArrayDeque<>(); // 뱀이 있는 자리
        deque.add(new int[]{0, 0});
        board[0][0] = 2;
        int d = 0, time = 0, nx = 0, ny = 0;
        while (true) {
            d = (d + dir[time++]) % 4;
            nx += dx[d];
            ny += dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 2) break; // 벽이나 자기자신과 부딪히면 끝
            if (board[nx][ny] == 1) { // 2. 사과가 있으면, 사과 없어지고 꼬리 움직이지 않음
                board[nx][ny] = 0;
            } else { // 3. 사과가 없으면, 몸 길이 줄여서 꼬리가 위치한 칸을 비워줌
                int[] last = deque.pollLast();
                board[last[0]][last[1]] = 0;
            }
            // 1. 머리를 다음칸에 위치
            deque.addFirst(new int[]{nx, ny});
            board[nx][ny] = 2; // 위에서 하면 사과 있는지 모름
        }
        return time;
    }
}
