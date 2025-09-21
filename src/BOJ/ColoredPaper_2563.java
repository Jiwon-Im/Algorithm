package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColoredPaper_2563 {
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 색종이 세장
        map = new boolean[100][100];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            check(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j]) ans++;
            }
        }
        System.out.println(ans);
    }

    static void check(int x, int y) {
        for (int i = x; i < x + 10 && i < 100; i++) {
            for (int j = y; j < y + 10 && j < 100; j++) {
                map[i][j] = true;
            }
        }
    }
}
