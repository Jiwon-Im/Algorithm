package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class zellda_4485 {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (!(N == 0)) {
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();
            sb.append("Problem ").append(idx++).append(": ").append(map[N - 1][N - 1]).append("\n");

            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        Queue<Point> que = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.v - o2.v;
            }
        });

        boolean[][] v = new boolean[N][N];
        // 출발
        que.offer(new Point(0, 0, map[0][0]));

        Point p = null;
        while (!que.isEmpty()) {
            p = que.poll();

            // 이미 방문한 지점이면 패스
            while (v[p.x][p.y]) {
                p = que.poll();
            }
            map[p.x][p.y] = p.v;
            v[p.x][p.y] = true;

            for (int d = 0; d < 4; d++) {
                int dr = p.x + dx[d];
                int dc = p.y + dy[d];

                if (dr < 0 || dr >= N || dc < 0 || dc >= N || v[dr][dc]) {
                    continue;
                }

                // find
                if (dr == N - 1 && dc == N - 1) {
                    map[dr][dc] += p.v;
                    return;
                }

                que.add(new Point(dr, dc, p.v + map[dr][dc]));
            }
        }

    }

    static private class Point {
        int x, y, v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
}
