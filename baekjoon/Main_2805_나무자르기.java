package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2805_나무자르기 {
    private static int N, M, trees[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);
        System.out.println(maxHeight());
    }

    private static int maxHeight() {
        int min = 0, max = trees[N - 1], mid;
        while (min <= max) {
            mid = (min + max) / 2;
            long sum = getSum(mid);
            if (sum >= M) min = mid + 1;
            else max = mid - 1;
        }
        return max;
    }

    private static long getSum(int mid) {
        long sum = 0;
        for (int i : trees) {
            if (i > mid) sum += i - mid;
        }
        return sum;
    }
}
