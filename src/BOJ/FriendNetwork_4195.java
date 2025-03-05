package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FriendNetwork_4195 {
    static int[] p;
    static int[] sum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());
            int cnt = 0;
            init(F + F);

            HashMap<String, Integer> map = new HashMap<>();

            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                int c, d;
                if (!map.containsKey(a)) {
                    map.put(a, cnt++);
                }
                if (!map.containsKey(b)) {
                    map.put(b, cnt++);
                }

                c = find(map.get(a));
                d = find(map.get(b));

                if (c != d) {
                    p[d] = c;
                    sum[c] += sum[d];
                }
                sb.append(sum[c]).append("\n");
            }

        }
        System.out.println(sb);
    }

    private static void init(int f) {
        p = new int[f + 1];
        sum = new int[f + 1];
        for (int i = 0; i < f + 1; i++) {
            p[i] = i;
            sum[i] = 1;
        }
    }

    static private int find(int a) {
        if (a == p[a]) {
            return a;
        }
        return p[a] = find(p[a]);
    }
}
