package template;

import java.util.Arrays;

public class 조합 {
    private static int n, m;
    private static int[] arr;

    public static void main(String[] args) {
        n = 4;
        m = 2;
        arr = new int[m];
        combination(0, 1);
        System.out.println("==============");
        repeatedCombination(0, 1);
    }

    // 조합
    private static void combination(int cnt, int start) {
        if (cnt == m) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = start; i <= n; i++) {
            arr[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }

    // 중복 조합
    private static void repeatedCombination(int cnt, int start) {
        if (cnt == m) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = start; i <= n; i++) {
            arr[cnt] = i;
            combination(cnt + 1, i);
        }
    }
}
