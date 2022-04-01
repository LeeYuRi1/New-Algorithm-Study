package programmers;

public class Solution_1829_카카오프렌즈컬러링북 {
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    private boolean[][] visited;
    private int M, N, num;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0, maxSizeOfOneArea = 0;
        M = m;
        N = n;
        num = 0;
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;
                numberOfArea++;
                num = 0;
                dfs(i, j, picture, picture[i][j]);
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, num);
            }
        }
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private void dfs(int x, int y, int[][] picture, int color) {
        visited[x][y] = true;
        num++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if (visited[nx][ny] || picture[nx][ny] != color) continue;
            dfs(nx, ny, picture, color);
        }
    }
}
