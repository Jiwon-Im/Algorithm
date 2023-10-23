package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1003
// 0 또는 1이 출력되는 횟수
public class Fibonacci_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] num = new int[T];

        for (int t = 0; t < T; t++) {
            num[t] = Integer.parseInt(br.readLine());
        }

        int[][] fibonacci = new int[2][41];
        fibonacci[0][0] = 1; // 0 개수 - 1개
        fibonacci[0][1] = 0; // 0 개수
        fibonacci[1][0] = 0; // 1 개수
        fibonacci[1][1] = 1; // 1 개수 - 1개
        for (int i = 2; i <= 40; i++) {
            fibonacci[0][i] = fibonacci[0][i - 1] + fibonacci[0][i - 2];
            fibonacci[1][i] = fibonacci[1][i - 1] + fibonacci[1][i - 2];
        }

        for (int n : num) {
            sb.append(fibonacci[0][n]).append(" ").append(fibonacci[1][n]).append("\n");
        }

        System.out.println(sb);
    }
}
