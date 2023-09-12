package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Princess7_1941_2 {
    static char[][] map;
    static boolean[][] v;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        v = new boolean[5][5];
        ans = 0;

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        select(0, 0, 0);
        System.out.println(ans);
    }

    private static void select(int idx, int cnt, int dsCnt) {

        // 7명 다 찾았을 때 & 이다솜파 4명 이상
        if (cnt == 7 && dsCnt >= 4) {
            // 자리 배치 확인
            bfs();
            return;
        }

        // idx 벗어날 때
        if (idx == 25) {
            return;
        }

        // dr, dc로 변환
        int r = idx / 5;
        int c = idx % 5;
        v[r][c] = true;
        if (map[r][c] == 'S') {
            select(idx + 1, cnt + 1, dsCnt + 1);
        } else {
            select(idx + 1, cnt + 1, dsCnt);
        }
        v[r][c] = false;
        select(idx + 1, cnt, dsCnt);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[5][5];
        Queue<Point> q = new LinkedList<>();

        loop:
        // 첫번째 탐색지점 찾기
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (v[i][j]) {
                    q.offer(new Point(i, j));
                    visited[i][j] = true;
                    break loop;
                }
            }
        }

        int cnt = 6;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int dx = p.x + dr[d];
                int dy = p.y + dc[d];
                if (dx < 0 || dx >= 5 || dy < 0 || dy >= 5 || visited[dx][dy]) {
                    continue;
                }

                visited[dx][dy] = true;

                if(v[dx][dy]) {
                    cnt--;
                    q.offer(new Point(dx, dy));
                }
            }
        }
        // 7명이 전부 붙어있을 경우
        if (cnt == 0) {
            ans++;
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
