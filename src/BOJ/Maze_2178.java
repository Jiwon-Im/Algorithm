package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maze_2178 {
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        que.offer(new Point(0, 0, 1));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dx = p.x + dr[d];
                int dy = p.y + dc[d];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M || map[dx][dy] == 0 || visited[dx][dy]) {
                    continue;
                }

                if (dx == N - 1 && dy == M - 1) {
                    return p.cnt + 1;
                }

                visited[dx][dy] = true;
                que.offer(new Point(dx, dy, p.cnt + 1));
            }

        }
        return 1;
    }

    private static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }
}
