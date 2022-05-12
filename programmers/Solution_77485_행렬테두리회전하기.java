package programmers;

public class Solution_77485_행렬테두리회전하기 {
    private int arr[][], answer[];
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public int[] solution(int rows, int columns, int[][] queries) {
        arr = new int[rows][columns];
        answer = new int[queries.length];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            rotation(x1, y1, x2, y2, i);
        }
        return answer;
    }

    private void rotation(int x1, int y1, int x2, int y2, int index) {
        int min = arr[x1][y1];
        int temp = arr[x1][y1];
        int x = x1, y = y1;
        for (int d = 0; d < 4; d++) {
            while (true) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < x1 || ny < y1 || nx > x2 || ny > y2) break;
                arr[x][y] = arr[nx][ny];
                x = nx;
                y = ny;
                min = Math.min(min, arr[x][y]);
            }
        }
        arr[x1][y1 + 1] = temp;
        answer[index] = min;
    }
}
