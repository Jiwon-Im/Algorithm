package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PuyoPuyo_11559 {

    static char[][] map;
    static boolean[][] v;
    static boolean flag = false;
    static int ans;
    static int N = 12, M = 6;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Stack<Point> stack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        play();
        System.out.println(ans);
    }

    private static void play() {

        // 폭탄찾기
        while (true) {
            // 초기화
            v = new boolean[N][M];
            flag = false;

            bomb();
            // 터트린 것 없음! 리턴
            if (!flag) {
                return;
            }
            ans++;
            // 아래로 내리기
            gravity();
        }

    }

    private static void bomb() {

        // 제일 왼쪽 열부터 탐색
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] != '.' && !v[i][j]) {
                    // 새로운 색의 블록 발견 - 폭탄 검사
                    stack = new Stack<>();
                    dfs(i, j);
                    if (stack.size() >= 4) {
                        // 4개 이상 모였다면 스택에 들어있는 좌표 터트리기(.으로 바꾸기) & 스택 비우기
                        bomb_stack();
                        // 터진 적이 있다 -> ans++
                        flag = true;
                    }
                    // 스택 비우기
                    stack.clear();
                }
            }
        }
    }

    private static void bomb_stack() {
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            map[p.x][p.y] = '.';
        }
    }

    private static void dfs(int r, int c) {
        stack.push(new Point(r, c));
        v[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int dr = r + dx[d];
            int dc = c + dy[d];

            if (dr < 0 || dr >= N || dc < 0 || dc >= M) {
                continue;
            }

            // 방문했거나 다른 값이라면 패스
            if (v[dr][dc] || map[r][c] != map[dr][dc]) {
                continue;
            }
            dfs(dr, dc);
        }
    }

    private static void gravity() {

        for (int j = 0; j < M; j++) {
            // 첫번째 빈 행 찾기
            int blankRow = N - 1;
            while (blankRow > 0 && map[blankRow][j] != '.') {
                blankRow--;
            }

            // 잘 쌓여있는지 확인
            for (int i = N - 2; i >= 0; i--) {
                if (map[i][j] != '.' && map[i + 1][j] == '.') {
                    map[blankRow--][j] = map[i][j]; // blank 위치에 값 넣고
                    map[i][j] = '.'; // .으로 바꾸기
                }
            }
        }

    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
