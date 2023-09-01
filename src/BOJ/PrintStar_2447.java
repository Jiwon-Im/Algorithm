package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintStar_2447 {
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // N은 3의 거듭제곱
        map = new boolean[N][N];

        dfs(0, 0, N / 3);       // 좌상단 좌표, 9영역으로 나눴을 때 한 영역의 길이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] ? ' ' : '*'); // true면 '공백' false면 '*' 출력
            }
            sb.append("\n");            // 줄바꿈
        }
        System.out.println(sb);
    }

    // 가운데 영역을 true로 체크하고 나머지 8개 영역을 dfs탐색하는 함수
    // F|F|F
    // F|T|F
    // F|F|F
    private static void dfs(int r, int c, int len) {

        // 가운데 영역 true로 check
        for (int i = r + len; i < r + len * 2; i++) {
            for (int j = c + len; j < c + len * 2; j++) {
                map[i][j] = true;
            }
        }

        // 종료조건 : 3*3 가장 작은 영역까지 처리 완료, 리턴
        if (len == 1) {
            return;
        }

        // 다음 N/3 영역에서 사용할 내부 영역의 길이 len
        int nextLen = len / 3;

        // 가운데 영역을 제외한 나머지 8개 영역 dfs
        // 상단(왼쪽/가운데/오른쪽)
        dfs(r, c, nextLen);
        dfs(r, c + len, nextLen);
        dfs(r, c + len + len, nextLen);
        // 중단(왼쪽/오른쪽)
        dfs(r + len, c, nextLen);
        dfs(r + len, c + len + len, nextLen);
        // 하단(왼쪽/가운데/오른쪽)
        dfs(r + len + len, c, nextLen);
        dfs(r + len + len, c + len, nextLen);
        dfs(r + len + len, c + len + len, nextLen);

    }
}
