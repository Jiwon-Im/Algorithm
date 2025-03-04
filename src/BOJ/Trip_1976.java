package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Trip_1976 {
    static int N, M;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        p = new int[N];
        initP();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    p[find(i)] = find(j);
                }
            }
        }
        boolean flag = true;
        st = new StringTokenizer(br.readLine());
        int a = find(Integer.parseInt(st.nextToken()) - 1);

        for (int i = 1; i < M; i++) {
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a == find(b)) {
                a = find(b);
            } else {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static private int find(int a) {
        if (a == p[a]) {
            return a;
        }
        return p[a] = find(p[a]);
    }

    static private void initP() {
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }
    }
}
