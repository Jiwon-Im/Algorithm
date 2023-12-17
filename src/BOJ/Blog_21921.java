package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/21921
public class Blog_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visitor = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visitor[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0, cnt = 0;

        for (int i = 0; i < X; i++) {
            sum += visitor[i];
        }
        if (sum > 0) cnt = 1;
        int max = sum;

        for (int i = 0, j = X; j < N; i++, j++) {
            sum += -visitor[i] + visitor[j];
            if (sum == max) {
                cnt++;
            } else if (sum > max) {
                max = sum;
                cnt = 1;
            }
        }
        if (max == 0) {
            sb.append("SAD");
        } else {
            sb.append(max).append("\n").append(cnt);
        }
        System.out.println(sb);
    }
}
