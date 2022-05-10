package template;

import java.util.Arrays;

public class 순열 {
    private static int n, m;
    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) {
        n = 4;
        m = 2;
        arr = new int[m];
        visited = new boolean[n + 1];
        permutation(0);
        System.out.println("==============");
        repeatedPermutation(0);
    }

    // 순열
    private static void permutation(int cnt) {
        if (cnt == m) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                permutation(cnt + 1);
                visited[i] = false;
            }
        }
    }

    // 중복 순열
    private static void repeatedPermutation(int cnt) {
        if (cnt == m) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = 1; i <= n; i++) {
            arr[cnt] = i;
            permutation(cnt + 1);
        }
    }
}
