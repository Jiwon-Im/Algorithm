package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HideAndSeek4_13913 {
    static int[] dx = {-1, 1};
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0 + "\n" + K);
            return;
        }

        Point p = bfs();
        StringBuilder sb = new StringBuilder();
        sb.append(p.cnt + 1).append("\n");
        for (int x : p.steps) {
            sb.append(x).append(" ");
        }
        sb.append(K);
        System.out.println(sb.toString());
    }

    private static Point bfs() {

        Queue<Point> que = new LinkedList<>();
        List<Integer> first = new ArrayList<>();
        first.add(N);
        que.offer(new Point(N, 0, first));
        boolean[] v = new boolean[200000];
        v[N] = true;

        while (!que.isEmpty()) {
            Point p = que.poll();
            List<Integer> steps = new ArrayList<>(p.steps);

            for (int d = 0; d < 2; d++) {
                int dr = p.x + dx[d];
                if (dr < 0 || dr >= 200000 || v[dr]) {
                    continue;
                }
                v[dr] = true;
                if (dr == K) {
                    return p;
                }
                List<Integer> nextSteps = new ArrayList<>(steps);
                nextSteps.add(dr);
                que.offer(new Point(dr, p.cnt + 1, nextSteps));
            }

            int dr = 2 * p.x;
            if (dr < 0 || dr >= 200000 || v[dr]) {
                continue;
            }
            if (dr == K) {
                return p;
            }
            v[dr] = true;
            List<Integer> nextSteps = new ArrayList<>(steps);
            nextSteps.add(dr);
            que.offer(new Point(dr, p.cnt + 1, nextSteps));
        }
        return null;
    }

    private static class Point {
        int x, cnt;
        List<Integer> steps;

        public Point(int x, int cnt, List<Integer> steps) {
            this.x = x;
            this.cnt = cnt;
            this.steps = steps;

        }
    }
}
