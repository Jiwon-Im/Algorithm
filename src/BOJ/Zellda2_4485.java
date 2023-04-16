package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Zellda2_4485 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            map = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(t++).append(": ").append(move()).append("\n");
        }

        System.out.println(sb.toString());
    }

    static private int move() {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0, map[0][0]));

        while (true) {
            Node node = pq.poll();

            // 이미 방문한 지점이면 패스
            while (visited[node.x][node.y]) {
                node = pq.poll();
            }

            visited[node.x][node.y] = true;

            if (node.x == N - 1 && node.y == N - 1) {
                return node.cnt;
            }

            // 탐색
            for (int d = 0; d < 4; d++) {
                int dr = node.x + dx[d];
                int dc = node.y + dy[d];

                if (dr < 0 || dr >= N || dc < 0 || dc >= N || visited[dr][dc]) {
                    continue;
                }

                int cnt = node.cnt + map[dr][dc];
                pq.offer(new Node(dr, dc, cnt));
            }
        }

    }

    static private class Node implements Comparable<Node> {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {

            if (this.cnt > o.cnt) {
                return 1;
            } else if (this.cnt == o.cnt) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
