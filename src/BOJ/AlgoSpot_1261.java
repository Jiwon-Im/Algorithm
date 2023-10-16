package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1261
public class AlgoSpot_1261 {
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
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
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] v = new boolean[N][M];
        // 시작점
        pq.add(new Point(0, 0, 0));
        v[0][0] = true;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            // 답
            if (p.x == N - 1 && p.y == M - 1) {
                return p.cnt;
            }
            // 방문 처리
            v[p.x][p.y] = true;
            for (int d = 0; d < 4; d++) {
                int dx = p.x + dr[d];
                int dy = p.y + dc[d];
                // 맵 벗어나거나 이전에 방문
                if (dx < 0 || dx >= N || dy < 0 || dy >= M || v[dx][dy]) {
                    continue;
                }
                v[dx][dy] = true;
                // 벽이라면 부수기
                if (map[dx][dy] == 1) {
                    pq.add(new Point(dx, dy, p.cnt + 1));
                }
                // 벽 아닐 때
                else {
                    pq.add(new Point(dx, dy, p.cnt));
                }
            }
        }

        return 0;
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt; // 벽 부순 횟수

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
}
