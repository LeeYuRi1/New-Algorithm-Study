package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13458_시험감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st2.nextToken());
        int C = Integer.parseInt(st2.nextToken());
        long ans = N;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st1.nextToken()) - B;
            if (num > 0) ans += num % C == 0 ? num / C : num / C + 1;
        }
        System.out.println(ans);
    }
}
