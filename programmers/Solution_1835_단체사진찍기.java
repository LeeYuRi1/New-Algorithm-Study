package programmers;

public class Solution_1835_단체사진찍기 {
    private char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private char[] arr;
    private int N = 8, answer = 0;

    public int solution(int n, String[] data) {
        arr = new char[N];
        permutation(new boolean[N], 0, data);
        return answer;
    }

    private void permutation(boolean[] visited, int cnt, String[] data) {
        if (cnt == N) {
            if (check(data)) answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = friends[i];
                permutation(visited, cnt + 1, data);
                visited[i] = false;
            }
        }
    }

    private boolean check(String[] data) {
        boolean result;
        for (String s : data) {
            int index1 = getIndex(s.charAt(0));
            int index2 = getIndex(s.charAt(2));
            int diff = Math.abs(index1 - index2) - 1;

            char op = s.charAt(3);
            int value = s.charAt(4) - '0';
            if (op == '=') result = (diff == value);
            else if (op == '<') result = (diff < value);
            else result = (diff > value);
            if (!result) return false;
        }
        return true;
    }

    private int getIndex(char c) {
        for (int i = 0; i < N; i++) {
            if (arr[i] == c) return i;
        }
        return -1;
    }
}
