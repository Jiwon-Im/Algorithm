package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Origami_2630 {
    static int[][] map;
    static int whiteNum = 0, blueNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        System.out.println(whiteNum);
        System.out.println(blueNum);
    }

    private static void dfs(int r, int c, int len) {

        // 종료조건 : 가장 작은 색종이가 될때까지
        if (len == 1) {
            // 색종이 색 count
            if (map[r][c] == 0) {
                whiteNum++;
            } else {
                blueNum++;
            }
            return;
        }

        // 전체 영역의 색이 같은 색인지 확인
        boolean isSameColor = true;
        int value = map[r][c]; // 첫번째 값 - 값이 같은지 확인하는 용도
        loop:
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (map[i][j] != value) {
                    isSameColor = false;
                    break loop;
                }
            }
        }

        // 같은 색일 때
        if (isSameColor) {
            // 색종이 색 count
            if (value == 0) {
                whiteNum++;
            } else {
                blueNum++;
            }
            return;
        }

        // 다른 색일 때 한번 더 접기
        int nextLen = len / 2;
        dfs(r, c, nextLen);
        dfs(r, c + nextLen, nextLen);
        dfs(r + nextLen, c, nextLen);
        dfs(r + nextLen, c + nextLen, nextLen);

    }
}
