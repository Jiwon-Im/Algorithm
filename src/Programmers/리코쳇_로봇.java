package Programmers;

import java.util.PriorityQueue;

public class 리코쳇_로봇 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static char[][] map;

    private static int bfs(Point p) {

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(p);
        boolean[][] v = new boolean[N][M];
        // v[p.x][p.y] = true;

        while (!pq.isEmpty()) {
            p = pq.poll();

            for (int d = 0; d < 4; d++) {
                int dr = p.x;
                int dc = p.y;

                // 장애물/벽 만날때까지 직진
                while (true) {
                    dr += dx[d];
                    dc += dy[d];

                    if (dr < 0 || dr >= N || dc < 0 || dc >= M || map[dr][dc] == 'D') {
                        break;
                    }
                }
                dr -= dx[d];
                dc -= dy[d];
                if (map[dr][dc] == 'G') {
                    return p.cnt + 1;
                }
                if (!v[dr][dc]) {
                    v[dr][dc] = true;
                    pq.add(new Point(dr, dc, p.cnt + 1));
                }

            }
        }
        return -1;
    }

    public int solution(String[] board) {

        N = board.length;
        M = board[0].length();
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = board[i].toCharArray();
        }

        // 출발점 찾기
        int answer = -1;
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    answer = bfs(new Point(i, j, 0));
                    break loop;
                }
            }
        }

        return answer;
    }

    private static class Point implements Comparable<Point> {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point p) {
            return this.cnt - p.cnt;
        }
    }
}