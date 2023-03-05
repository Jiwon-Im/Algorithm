package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MonkeyWantToBeHorse_1600 {

    static int K, H, W;
    static int[][] map;
    static int[] mdx = {-1, 1, 0, 0};
    static int[] mdy = {0, 0, -1, 1};
    static int[] hdx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] hdy = {2, 1, -1, -2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 가로
        H = Integer.parseInt(st.nextToken()); // 세로

        if (W == 1 && H == 1) {
            System.out.println(0);
            return;
        }

        map = new int[H][W];

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    static private int bfs() {
        boolean[][][] v = new boolean[H][W][K + 1];
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, K, 0));
        v[0][0][K] = true;

        while (!que.isEmpty()) {
            Point p = que.poll();

            // [원숭이 움직임]
            for (int d = 0; d < 4; d++) {
                int dr = p.x + mdx[d];
                int dc = p.y + mdy[d];

                if (dr < 0 || dr >= H || dc < 0 || dc >= W || v[dr][dc][p.k] || map[dr][dc] == 1) {
                    continue;
                }
                v[dr][dc][p.k] = true;
                if (dr == H - 1 && dc == W - 1) {
                    return p.cnt + 1;
                }
                que.offer(new Point(dr, dc, p.k, p.cnt + 1));
            }

            // [말 움직임]
            if (p.k > 0) {
                for (int d = 0; d < 8; d++) {
                    int dr = p.x + hdx[d];
                    int dc = p.y + hdy[d];

                    if (dr < 0 || dr >= H || dc < 0 || dc >= W || v[dr][dc][p.k - 1] || map[dr][dc] == 1) {
                        continue;
                    }
                    v[dr][dc][p.k - 1] = true;
                    if (dr == H - 1 && dc == W - 1) {
                        return p.cnt + 1;
                    }
                    que.offer(new Point(dr, dc, p.k - 1, p.cnt + 1));
                }
            }
        }
        return -1;
    }

    static private class Point {
        int x, y, k, cnt;

        public Point(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cnt = cnt;
        }
    }

}
