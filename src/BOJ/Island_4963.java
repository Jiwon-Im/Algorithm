package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Island_4963 {

    static int H, W;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) {
                break;
            }

            map = new int[H][W];

            for (int h = 0; h < H; h++) {
                st = new StringTokenizer(br.readLine());

                for (int w = 0; w < W; w++) {
                    map[h][w] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(find()).append("\n");
        }
        System.out.println(sb);
    }

    private static int find() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                // 섬 발견
                if (map[i][j] == 1) {
                    cnt++;
                    dfs(i, j);
                }
            }
        }
        return cnt;
    }

    private static void dfs(int r, int c) {

        for (int d = 0; d < 8; d++) {
            int dr = r + dx[d];
            int dc = c + dy[d];

            if (dr < 0 || dr >= H || dc < 0 || dc >= W) {
                continue;
            }

            // 연결된 섬 찾기
            if (map[dr][dc] == 1) {
                map[dr][dc] = 0;
                dfs(dr, dc);
            }
        }
    }
}
