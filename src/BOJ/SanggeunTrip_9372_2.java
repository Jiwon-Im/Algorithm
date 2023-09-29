package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SanggeunTrip_9372_2 {
    static int[] parents;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            parents = new int[N + 1];
            initP();

            int M = Integer.parseInt(st.nextToken());
            int cnt = 0;

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                a = find(a);
                b = find(b);

                if (a == b) {
                    continue;
                }

                parents[b] = a;
                cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    private static void initP() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }
}
