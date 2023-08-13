package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Watch_15683 {
    static int N, M;
    static int[][][] directions = {{},  // 0 - 남는 idx
            {{0}, {1}, {2}, {3}},       // 1 - 상하좌우
            {{2, 3}, {1, 0}},           // 2 - 좌우/상하
            {{1, 3}, {3, 0}, {0, 2}, {2, 1}}, // 3 - 상우/우하/하좌/좌상
            {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}}, // 4 - 상하좌우 제외
            {{0, 1, 2, 3}}};    // 5 - 한가지 경우
    static ArrayList<Point> watches;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        watches = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value > 0 && value < 6) {
                    watches.add(new Point(i, j));
                }
                map[i][j] = value;
            }
        }

        dfs(0, map);
        System.out.println(min);
    }

    private static void dfs(int idx, int[][] lastMap) {

        if (idx == watches.size()) {
            count();
            return;
        }

        Point p = watches.get(idx);
        for (int[] directCase : directions[lastMap[p.x][p.y]]) {
            copy(lastMap);
            watching(p, directCase);
            dfs(idx + 1, map);
        }
    }

    private static void copy(int[][] lastMap) {
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = lastMap[i][j];
            }
        }
    }

    private static void count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        min = Math.min(min, cnt);
    }

    private static void watching(Point p, int[] direct) {
        for (int d : direct) {
            int x = p.x;
            int y = p.y;

            while (true) {
                x += dx[d];
                y += dy[d];

                if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6) {
                    break;
                } else if (map[x][y] == 0) {
                    map[x][y] = -1;
                }
            }
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
