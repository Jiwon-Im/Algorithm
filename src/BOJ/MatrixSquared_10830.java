package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10830
public class MatrixSquared_10830 {
    static int N;
    static long B;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        map = divide(B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] divide(long b) {

        if (b == 1L) {
            return map;
        }

        // 홀수일 때
        if (b % 2 == 1L) {
            return calculate(map, divide(b - 1));
        }

        // 짝수일 때 - 반으로 나누기
        int[][] half = divide(b / 2);
        return calculate(half, half);
    }

    private static int[][] calculate(int[][] a, int[][] b) {
        // 행렬 연산
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % 1000;
                }
            }
        }
        return result;
    }
}
