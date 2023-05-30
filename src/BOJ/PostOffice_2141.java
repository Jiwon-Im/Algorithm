package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PostOffice_2141 {
    static int N;
    static int[] loc;
    static int[] people;
    static int ans;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        loc = new int[N];
        people = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            loc[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            calc(i);
        }
        System.out.println(loc[ans]);

    }

    private static void calc(int x) {
        long sum = 0;
        for (int i = 0; i < x; i++) {
            sum += (long) people[i] * Math.abs(loc[x] - loc[i]);
        }

        for (int i = x + 1; i < N; i++) {
            sum += (long) people[i] * Math.abs(loc[x] - loc[i]);
        }

        if (sum < min) {
            ans = x;
            min = sum;
        }
    }
}
