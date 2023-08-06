package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartLink_5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken()); // 층 수
        int S = Integer.parseInt(st.nextToken()); // 강호 위치
        int G = Integer.parseInt(st.nextToken()); // 스타트링크 위치 // 목적지
        int U = Integer.parseInt(st.nextToken()); // UP
        int D = -Integer.parseInt(st.nextToken()); // DOWN

        boolean[] v = new boolean[F + 1];
        Queue<Integer> que = new LinkedList<>();
        que.offer(S);
        v[S] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int p = que.poll();
                if (p == G) {
                    System.out.println(cnt);
                    return;
                }

                int up = p + U;
                if (up <= F && !v[up]) {
                    que.offer(up);
                    v[up] = true;
                }

                int down = p + D;
                if (down > 0 && !v[down]) {
                    que.offer(down);
                    v[down] = true;
                }
            }
            cnt++;
        }
        System.out.println("use the stairs");

    }
}
