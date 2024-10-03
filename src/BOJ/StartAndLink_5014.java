package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartAndLink_5014 {
    static int F, S, G, U, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        find(S);

    }

    private static void find(int s) {

        Queue<Location> que = new LinkedList<>();
        que.offer(new Location(s, 0));
        boolean[] v = new boolean[F + 1];

        while (!que.isEmpty()) {
            Location now = que.poll();

            if(v[now.loc]) continue;
            v[now.loc] = true;

            if (now.loc == G) {
                System.out.println(now.count);
                return;
            }

            int next = now.loc + U;
            if (next < F + 1 && !v[next]) {
                que.offer(new Location(next, now.count + 1));
            }
            next = now.loc - D;
            if (next > 0 && !v[next]) {
                que.offer(new Location(next, now.count + 1));
            }
        }
        System.out.println("use the stairs");
    }

    static class Location {
        int loc;
        int count;

        public Location(int loc, int count) {
            this.loc = loc;
            this.count = count;
        }
    }
}
