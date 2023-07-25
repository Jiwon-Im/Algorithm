package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet_1987 {
    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - 'A';
            }
        }

        boolean[] alpha = new boolean[26];
        alpha[map[0][0]] = true;

        dfs(0, 0, 1, alpha);
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int cnt, boolean[] alhpa) {
        // 카운트 갱신
        ans = Math.max(ans, cnt);

        for (int d = 0; d < 4; d++) {
            int dx = r + dr[d];
            int dy = c + dc[d];

            // 맵 벗어남 / 방문 / 사용한 알파벳
            if (dx < 0 || dx >= R || dy < 0 || dy >= C || alhpa[map[dx][dy]]) {
                continue;
            }

            // 백트래킹
            alhpa[map[dx][dy]] = true;
            dfs(dx, dy, cnt + 1, alhpa);
            alhpa[map[dx][dy]] = false;
        }
    }
}
