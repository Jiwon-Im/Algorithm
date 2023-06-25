package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ControlRobot_2169 {
    static int N, M;
    static int[][] map;
    static int[] dx = {0, 0, 1};
    static int[] dy = {-1, 1, 0};
    static boolean[][] v;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(max);
    }

    static private void dfs(int x, int y, int sum) {
        // 방문 체크, 값 더하기
        v[x][y] = true;
        sum += map[x][y];

        if (x == N - 1 && y == M - 1) {
            // 최댓값 check
            max = Math.max(max, sum);
            // return
            return;
        }

        for (int d = 0; d < 3; d++) {
            int dr = x + dx[d];
            int dc = y + dy[d];

            if (dr < 0 || dr >= N || dc < 0 || dc >= M || v[dr][dc]) {
                continue;
            }

            dfs(dr, dc, sum);
            v[dr][dc] = false;
        }
    }
}
