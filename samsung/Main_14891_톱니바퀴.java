package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    private static int[][] wheel;
    private static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wheel = new int[4][8];
        for (int i = 0; i < 4; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Character.getNumericValue(input[j]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            check = new int[4];
            checkDir(n, d);
            for (int i = 0; i < 4; i++) {
                if (check[i] != 0) rotation(i);
            }
        }

        int ans = 0;
        for (int i = 0, score = 1; i < 4; i++, score *= 2) {
            if (wheel[i][0] == 1) ans += score;
        }
        System.out.println(ans);
    }

    // 어느 톱니바퀴를 무슨 방향으로 돌릴지 결정
    private static void checkDir(int n, int d) {
        // 오른쪽 확인
        check[n] = d;
        for (int i = n; i < 3; i++) {
            if (wheel[i][2] == wheel[i + 1][6]) break; // 오른쪽 2, 왼쪽 6
            check[i + 1] = check[i] * -1;
        }
        // 왼쪽 확인
        for (int i = n; i > 0; i--) {
            if (wheel[i][6] == wheel[i - 1][2]) break;
            check[i - 1] = check[i] * -1;
        }
    }

    // 톱니바퀴 회전
    private static void rotation(int n) {
        int temp;
        if (check[n] == 1) { // 시계방향
            temp = wheel[n][7];
            for (int i = 7; i > 0; i--) {
                wheel[n][i] = wheel[n][i - 1];
            }
            wheel[n][0] = temp;
        } else { // 반시계방향
            temp = wheel[n][0];
            for (int i = 0; i < 7; i++) {
                wheel[n][i] = wheel[n][i + 1];
            }
            wheel[n][7] = temp;
        }
    }
}
