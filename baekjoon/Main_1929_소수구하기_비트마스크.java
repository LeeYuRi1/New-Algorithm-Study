package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 192ms, 17448KB
public class Main_1929_소수구하기_비트마스크 {
    private static char[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        check = new char[(N + 7) / 8 + 1];
        Arrays.fill(check, (char) 255);
        setComposite(0);
        setComposite(1);
        for (int i = 2; i * i <= N; i++) {
            if (!isPrime(i)) continue;
            for (int j = i + i; j <= N; j += i) {
                setComposite(j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = M; i <= N; i++) {
            if (isPrime(i)) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isPrime(int n) {
        return (check[n >> 3] & (1 << (n & 7))) != 0;
    }

    private static void setComposite(int n) {
        check[n >> 3] &= ~(1 << (n & 7));
    }
}
