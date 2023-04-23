package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen_9663 {
    static boolean[][] map;
    static int N;
    static int cnt;
    static int[] cols;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        cols = new int[N];

        if (N == 1) {
            System.out.println(1);
            return;
        }

        // 각 행별로 퀸 하나씩
        for (int i = 0; i < N / 2; i++) {
            cols[0] = i;
            dfs(1);
        }
        cnt *= 2;
        if (N % 2 == 1) {
            cols[0] = N / 2;
            dfs(1);
        }
        System.out.println(cnt);
    }

    private static void dfs(int depth) {
        // 모든 행에 퀸을 놓았다.
        if (depth == N) {
            cnt++;
            return;
        }

        // depth 행의 j 열에 퀸을 놓을 수 있는지 검사
        for (int j = 0; j < N; j++) {
            if (possible(depth, j)) { // 놓을 수 있다면
                cols[depth] = j;  // depth 행의 퀸의 위치는 j라고 표시
                dfs(depth + 1); // 다음 행으로
            }
        }

    }

    private static boolean possible(int depth, int cur) {
        // 현재 행보다 윗 행들 탐색
        // 같은 열에 놓인 퀸이 있는지 확인
        for (int i = 0; i < depth; i++) {
            if (cols[i] == cur) {
                return false;
            }
        }
        // 같은 대각선에 일치하는 게 있는지 확인
        for (int i = depth - 1, j = 1; i >= 0; i--, j++) {
            // 왼쪽방향, 오른쪽 방향 (j를 이용해서 열 이동)
            if (cols[i] == cur - j || cols[i] == cur + j) {
                return false;
            }
        }
        return true;
    }

}
