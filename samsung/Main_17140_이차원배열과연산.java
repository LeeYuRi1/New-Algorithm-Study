package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17140_이차원배열과연산 {
    private static int lenR, lenC, arr[][];

    private static class Number implements Comparable<Number> {
        int n, count;

        public Number(int n, int count) {
            this.n = n;
            this.count = count;
        }

        @Override
        public int compareTo(Number o) {
            if (this.count == o.count) return Integer.compare(this.n, o.n);
            return Integer.compare(this.count, o.count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        arr = new int[100][100];
        lenR = 3; // 행의 개수
        lenC = 3; // 열의 개수
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while (arr[r][c] != k) {
            if (time > 100) break;
            time++;
            if (lenR >= lenC) operR();
            else operC();
        }
        System.out.println(time > 100 ? -1 : time);
    }

    // 모든 행에 대해서 정렬 수행
    private static void operR() {
        int max = 0;
        for (int i = 0; i < lenR; i++) {
            int[] count = new int[101];
            // 각 수가 몇번 나오는지 저장
            for (int j = 0; j < lenC; j++) {
                count[arr[i][j]]++;
                arr[i][j] = 0; // 원래 자리 0으로 만들기!!
            }
            // list에 넣어서 정렬
            List<Number> list = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                if (count[j] != 0) list.add(new Number(j, count[j]));
            }
            Collections.sort(list);
            // 배열에 넣어주기
            int index = 0;
            for (int j = 0; j < list.size(); j++) {
                arr[i][index++] = list.get(j).n;
                arr[i][index++] = list.get(j).count;
                if (index >= 100) break;
            }
            max = Math.max(max, index);
        }
        lenC = max;
    }

    // 모든 열에 대해서 정렬 수행
    private static void operC() {
        int max = 0;
        for (int j = 0; j < lenC; j++) {
            int[] count = new int[101];
            // 각 수가 몇번 나오는지 저장
            for (int i = 0; i < lenR; i++) {
                count[arr[i][j]]++;
                arr[i][j] = 0;
            }
            // list에 넣어서 정렬
            List<Number> list = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                if (count[i] != 0) list.add(new Number(i, count[i]));
            }
            Collections.sort(list);
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                arr[index++][j] = list.get(i).n;
                arr[index++][j] = list.get(i).count;
                if (index >= 100) break;
            }
            max = Math.max(max, index);
        }
        lenR = max;
    }
}
