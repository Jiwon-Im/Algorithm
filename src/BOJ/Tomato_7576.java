package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato_7576 {
    static int[][] map;
    static int M, N;
    // 안익은 토마토
    static int left = 0;
    static Queue<Point> que;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                // 익은 토마토 큐에 넣기
                if (value == 1) {
                    que.offer(new Point(i, j));
                    // 방문처리
                    map[i][j] = -1;
                }
                // 안익은 토마토 개수 세기
                else if (value == 0) {
                    left++;
                }
            }
        }
        // 익은 토마토 주변 너비우선 탐색
        int cnt = bfs();
        if (left == 0) {
            // 최소 날짜 출력
            System.out.println(cnt);
        } else {
            // 남은 토마토가 있음
            System.out.println(-1);
        }
    }

    private static int bfs() {
        // 날짜 카운트
        int cnt = 0;

        // 큐에 익은 토마토 남아있고 덜익은 토마토도 남아있을 때까지만 탐색
        while (!que.isEmpty() && left > 0) {
            // 날짜 세기
            cnt++;
            // 큐 사이즈(익은 토마토)만큼 주변 탐색
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Point p = que.poll();
                for (int d = 0; d < 4; d++) {
                    int dr = p.x + dx[d];
                    int dc = p.y + dy[d];
                    // 맵 벗어나거나 방문한 토마토일 경우 continue
                    if (dr < 0 || dr >= N || dc < 0 || dc >= M || map[dr][dc] == -1) {
                        continue;
                    }
                    // 큐에 넣고 방문처리 & 남은 토마토 개수 줄이기
                    que.offer(new Point(dr, dc));
                    map[dr][dc] = -1;
                    left--;
                }
            }

        }
        return cnt;
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
