package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2146
public class Bridge_2146 {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    dfs(i, j, islandNum++);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    bfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(min);
    }

    private static void dfs(int x, int y, int num) {
        map[x][y] = num;
        for (int d = 0; d < 4; d++) {
            int dr = x + dx[d];
            int dc = y + dy[d];

            if (dr < 0 || dr >= N || dc < 0 || dc >= N) {
                continue;
            }

            if (map[dr][dc] == 1) {
                dfs(dr, dc, num);
            }
        }
    }

    private static void bfs(int x, int y, int islandNum) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(x, y, 0));
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;

        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int d = 0; d < 4; d++) {
                int dr = p.x + dx[d];
                int dc = p.y + dy[d];

                if (dr < 0 || dr >= N || dc < 0 || dc >= N || visited[dr][dc]) {
                    continue;
                }

                if (map[dr][dc] == 0) {
                    visited[dr][dc] = true;
                    que.offer(new Point(dr, dc, p.cnt + 1));
                } else if (map[dr][dc] != islandNum) {
                    min = Math.min(min, p.cnt);
                    return;
                }
            }
        }
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
