package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
    private static int N, M, x, y, map[][], dice[];
    private static int[] dx = {0, 0, -1, 1}; // 동서북남
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기
        x = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 x좌표
        y = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 y좌표
        int K = Integer.parseInt(st.nextToken()); // 명령의 개수
        map = new int[N][M]; // 지도
        dice = new int[7]; // 주사위
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            if (move(Integer.parseInt(st.nextToken()) - 1)) sb.append(dice[1]).append("\n"); // 범위를 벗어나지 않으면 상단 값 넣기
        }
        System.out.println(sb);
    }

    private static boolean move(int d) {
        if (!isRange(x + dx[d], y + dy[d])) return false;
        x += dx[d];
        y += dy[d];
        rotateDice(d);
        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
        return true;
    }

    private static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < M;
    }

    private static void rotateDice(int d) {
        if (d == 0) rotate(1, 4, 6, 3);
        else if (d == 1) rotate(1, 3, 6, 4);
        else if (d == 2) rotate(1, 5, 6, 2);
        else rotate(1, 2, 6, 5);
    }

    private static void rotate(int d1, int d2, int d3, int d4) {
        int temp = dice[d1];
        dice[d1] = dice[d2];
        dice[d2] = dice[d3];
        dice[d3] = dice[d4];
        dice[d4] = temp;
    }
}
