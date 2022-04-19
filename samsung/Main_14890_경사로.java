package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890_경사로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken()); // 경사로의 길이
        int[][] area = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[j] = area[j][i];
            }
            if (isPass(area[i], L)) count++;
            if (isPass(arr, L)) count++;
        }
        System.out.println(count);
    }

    private static boolean isPass(int[] arr, int L) {
        boolean[] check = new boolean[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i];
            int next = arr[i + 1];
            if (cur == next) continue;
            if (Math.abs(cur - next) > 1) return false; // 높이 차이가 1보다 크면 지나갈 수 없음
            // 작은 부분을 검사해서 길이가 L보다 짧으면 경사로를 놓을 수 없음
            int count = 0;
            if (cur < next) {
                for (int j = i; j >= 0; j--) {
                    if (arr[i] != arr[j] || check[j] || count++ == L) break;
                    check[j] = true;
                }
            } else {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i + 1] != arr[j] || check[j] || count++ == L) break;
                    check[j] = true;
                }
            }
            if (count < L) return false;
        }
        return true;
    }
}
