package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 136ms, 16344KB
public class Main_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        int[] belt = new int[N]; // 벨트에 있는 초밥
        int[] cnt = new int[d + 1]; // 초밥 카운트

        int max = 0;
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
            if (i < k) {
                if (cnt[belt[i]] == 0) max++;
                cnt[belt[i]]++;
            }
        }

        int count = max;
        for (int i = 0; i < N; i++) {
            if (count >= max) {
                if (cnt[c] == 0) max = count + 1;
                else max = count;
            }
            if (--cnt[belt[i]] == 0) count--;
            if (cnt[belt[(i + k) % N]]++ == 0) count++;
        }
        System.out.println(max);
    }
}
