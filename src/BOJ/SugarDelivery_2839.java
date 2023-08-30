package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SugarDelivery_2839 {
    static int[] num;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        num = new int[N + 1];
        v = new boolean[N + 1];

        v[3] = true;
        num[3] = 1;
        if (N > 4) {
            v[5] = true;
            num[5] = 1;
        }

        int ans = find(N);
        if (ans == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    private static int find(int n) {
        if (n < 3) return 0;
        if (v[n]) {
            return num[n];
        }
        v[n] = true;
        int small = find(n - 3);
        int big = find(n - 5);

        if (small == 0) {
            if (big == 0) {
                return num[n] = 0;
            }
            return num[n] = big + 1;
        }
        else if (big == 0) {
            return num[n] = small + 1;
        }
        return num[n] = Math.min(1 + find(n - 3), 1 + find(n - 5));
    }
}
