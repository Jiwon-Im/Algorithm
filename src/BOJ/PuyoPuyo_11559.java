package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PuyoPuyo_11559 {

    static char[][] map;
    static boolean[][] v;
    static int cnt;
    static int num;
    static int ans;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 12;
        M = 6;
        map = new char[N][M];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        play();
        System.out.println(cnt);

    }

    private static void play() {

        v = new boolean[N][M];
        bomb();
        gravity();

        ans++;

    }

    private static void bomb() {

        // 제일 왼쪽 열부터 탐색
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] != '.' && !v[i][j]) {
                    num = 1;
                    dfs(i, j);
                    if (num >= 4) {
                        cnt++;
                    }
                }

            }

        }
    }

    private static void dfs(int r, int c) {
        v[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int dr = r + dx[d];
            int dc = c + dy[d];

            if (dr < 0 || dr >= N || dc < 0 || dc >= M) {
                continue;
            }

            // 방문했거나 다른 값이라면 패스
            if (v[dr][dc] || map[r][c] != map[dr][dc]) {
                continue;
            }

            num++;

            dfs(dr, dc);
        }

    }

    private static void gravity() {

    }
}
