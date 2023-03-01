package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


//다음 경로로 가는 방법은 3가지이지만
//현재 위치로 오는 방법은 1가지! -> 기록


public class HideAndSeek4_13913 {
    static int N, K;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0 + "\n" + K);
            return;
        }

        // 탐색
        Point p = bfs();

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(p.cnt + 1).append("\n");

        Stack<Integer> stack = new Stack<>();
        int last = parent[K];
        while (last != N) {
            stack.push(last);
            last = parent[last];
        }
        // 스택 이용해서 거꾸로 출력
        sb.append(N);
        while (!stack.isEmpty()) {
            sb.append(" ").append(stack.pop());
        }
        sb.append(" ").append(K);

        System.out.println(sb.toString());
    }

    private static Point bfs() {

        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(N, 0)); // 시작점
        boolean[] v = new boolean[110000];
        v[N] = true; // 시작지점 방문
        parent = new int[110000]; // 루트 저장

        while (!que.isEmpty()) {
            Point p = que.poll();

            // 이동 - 좌, 우, 2배
            int[] move = new int[]{p.x - 1, p.x + 1, 2 * p.x};

            for (int d = 0; d < 3; d++) {
                int dr = move[d];
                if (dr < 0 || dr >= 110000 || v[dr]) {
                    continue;
                }
                // 방문 & 루트 저장
                v[dr] = true;
                parent[dr] = p.x;
                // 찾았다
                if (dr == K) {
                    return p;
                }
                que.offer(new Point(dr, p.cnt + 1));
            }
        }
        return null;
    }

    private static class Point {
        int x, cnt;

        public Point(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
}
