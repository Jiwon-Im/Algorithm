package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree_1992 {
    static int[][] map;
    static int[][] next;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        map = new int[N][N];
        next = new int[2][4];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0, N);
        System.out.println(sb);
    }

    private static void dfs(int r, int c, int len) {
        // 시작
        sb.append('(');

        // 다음 길이
        int nextLen = len / 2;

        sectionCheck(r, c, nextLen);// 1구역
        sectionCheck(r, c + nextLen, nextLen);
        sectionCheck(r + nextLen, c, nextLen);
        sectionCheck(r + nextLen, c + nextLen, nextLen);
        // 끝
        sb.append(')');
    }

    private static void sectionCheck(int r, int c, int len) {
        if (isSameValue(r, c, len)) {
            // 모두 같은 값이면 종료 (dfs 종료조건 - 1개 남았을 때는 무조건 리턴)
            sb.append(map[r][c]);
        } else {
            dfs(r, c, len);

        }
    }

    private static boolean isSameValue(int r, int c, int len) {
        // 모든 값이 같은지 확인하는 함수
        int value = map[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                // 다른 값 존재
                if (map[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
