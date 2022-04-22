package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간초과 요인
// 1. 정렬을 하지 않아야 함 - 입력에서 주어지는 나무의 위치가 모두 다르므로 새로 추가하는 나무들을 list 앞에 넣어주면 됨
// 2. list에서 원소삭제시 iterator 사용하면 O(1)
public class Main_16235_나무재테크 {
    private static int N, area[][], plus[][];
    private static List<Tree> treeList;
    private static Queue<Tree> dieTreeQueue;
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    private static class Tree {
        int x, y, z;

        public Tree(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 땅 크기
        int M = Integer.parseInt(st.nextToken()); // 나무의 개수
        int K = Integer.parseInt(st.nextToken());
        area = new int[N][N];
        plus = new int[N][N];
        treeList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                plus[i][j] = Integer.parseInt(st.nextToken());
                area[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x, y, z));
        }

        for (int i = 0; i < K; i++) {
            if (treeList.size() == 0) break;
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(treeList.size());
    }

    // 자신의 나이만큼 양분을 먹고, 나이가 1증가
    // 한 칸에 여러개 나무 있으면, 어린 나무부터 양분을 먹음. 양분을 못먹으면 죽음
    private static void spring() {
        dieTreeQueue = new LinkedList<>();
        Iterator<Tree> it = treeList.iterator();
        while (it.hasNext()) {
            Tree t = it.next();
            if (area[t.x][t.y] >= t.z) { // 양분을 먹을 수 있음
                area[t.x][t.y] -= t.z;
                t.z++;
            } else {
                dieTreeQueue.offer(t);
                it.remove(); // treeList에서 삭제하면 ConcurrentModification 오류남
            }
        }
    }

    // 봄에 죽은 나무가 양분으로 변함 (양분 = 죽은 나무의 나이 / 2)
    private static void summer() {
        while (!dieTreeQueue.isEmpty()) {
            Tree t = dieTreeQueue.poll();
            area[t.x][t.y] += t.z / 2;
        }
    }

    // 나이가 5의 배수인 나무만 인접한 8칸에 번식
    private static void fall() {
        List<Tree> addTreeList = new LinkedList<>();
        for (Tree t : treeList) {
            if (t.z % 5 != 0) continue;
            for (int i = 0; i < 8; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                addTreeList.add(new Tree(nx, ny, 1));
            }
        }
        treeList.addAll(0, addTreeList);
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] += plus[i][j];
            }
        }
    }
}
