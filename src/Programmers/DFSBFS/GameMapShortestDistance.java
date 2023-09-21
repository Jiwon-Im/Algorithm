package Programmers.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

class GameMapShortestDistance {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] v;
    static int N, M;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        v = new boolean[N][M];

        return bfs(maps);
    }

    public int bfs(int[][] map) {

        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0, 1));
        v[0][0] = true;

        while (!que.isEmpty()) {
            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dx = p.x + dr[d];
                int dy = p.y + dc[d];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M || v[dx][dy] || map[dx][dy] == 0) {
                    continue;
                }

                v[dx][dy] = true;
                if (dx == N - 1 && dy == M - 1) {
                    return p.cnt + 1;
                }
                que.add(new Point(dx, dy, p.cnt + 1));
            }
        }

        return -1;
    }

    public class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}