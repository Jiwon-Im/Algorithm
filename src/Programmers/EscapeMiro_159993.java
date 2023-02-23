package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeMiro_159993 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M; // 맵 크기
    static Queue<Point> que;
    static String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
    static char[][] map;
    static boolean[][] v;

    public static void main(String[] args) {

        // 맵 char 배열로 변경
        makeCharArray();
        // 출발지점 찾기
        findStartPoint(map);

        // bfs 탐색 - 레버
        int cnt = bfs('L');
        if (cnt < 0) {
            System.out.println(-1);
            return;
        }

        // bfs 탐색 - exit
        int cnt2 = bfs('E');
        if (cnt2 < 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(cnt+" "+cnt2);
        System.out.println(cnt + cnt2);
    }

    private static void makeCharArray() {
        N = maps.length; // 맵 크기
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = maps[i].toCharArray();
        }
        M = map[0].length;
    }

    private static int bfs(char target) {
        int cnt = 0;

        while (!que.isEmpty()) {
            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dr = p.x + dx[d];
                int dc = p.y + dy[d];

                // 맵 벗어남 or 방문 or 벽 continue
                if (dr < 0 || dr >= N || dc < 0 || dc >= M|| v[dr][dc] || map[dr][dc] == 'X') {
                    continue;
                }

                // 방문처리
                v[dr][dc] = true;

                if (map[dr][dc] == target) {
                    // 다시 세팅
                    que = new LinkedList<>();
                    que.add(new Point(dr, dc));
                    v = new boolean[N][M];
                    v[dr][dc] = true;
                    return cnt + 1;
                }
                que.add(new Point(dr, dc));
            }
            cnt++;
        }
        return -1;
    }

    private static void findStartPoint(char[][] map) {
        v = new boolean[N][M];
        que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    v[i][j] = true;
                    que.add(new Point(i, j));
                    return;
                }
            }
        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
