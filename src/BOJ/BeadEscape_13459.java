package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BeadEscape_13459 {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int targetR, targetC;

    // 빨간 구슬을 구멍을 통해 빼내는 게임
    // 왼쪽으로 기울이기/오른쪽으로 기울이기/위쪽으로 기울이기/아래쪽으로 기울이기
    // 10번 이하로 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(findAnswer());

    }

    private static int findAnswer() {

        boolean[][][][] v = new boolean[N][M][N][M];

        Queue<Bead> q = new LinkedList<>();
        q.offer(findStartLoc());

        while (!q.isEmpty()) {
            Bead p = q.poll();

            if (v[p.redR][p.redC][p.blueR][p.blueC]) {
                continue;
            }

            v[p.redR][p.redC][p.blueR][p.blueC] = true;

            for (int d = 0; d < 4; d++) {
                Point red, blue;

                // 위쪽 굴리기(col 같을때)
                if (d == 0 && p.redC == p.blueC) {
                    // 더 왼쪽이 빨간공일때
                    if (p.redR < p.blueR) {
                        // 빨간공 굴리기
                        red = roll(p.redR, p.redC, d);
                        blue = roll(p.blueR, p.blueC, d);
                    } else {
                        // 파란공 굴리기
                        blue = roll(p.blueR, p.blueC, d);
                        red = roll(p.redR, p.redC, d);
                    }
                }
                // 아래쪽 굴리기(col 같을때)
                else if (d == 1 && p.redC == p.blueC) {
                    if (p.redR > p.blueR) {
                        // 빨간공 굴리기
                        red = roll(p.redR, p.redC, d);
                        blue = roll(p.blueR, p.blueC, d);
                    } else {
                        // 파란공 굴리기
                        blue = roll(p.blueR, p.blueC, d);
                        red = roll(p.redR, p.redC, d);
                    }
                }
                // 왼쪽 굴리기(row 같을때)
                else if (d == 2 && p.redR == p.blueR) {
                    // 더 왼쪽이 빨간공일때
                    if (p.redC < p.blueC) {
                        // 빨간공 굴리기
                        red = roll(p.redR, p.redC, d);
                        blue = roll(p.blueR, p.blueC, d);
                        if (red.c == blue.c) {
                            red.c--;
                        }
                    } else {
                        // 파란공 굴리기
                        blue = roll(p.blueR, p.blueC, d);
                        red = roll(p.redR, p.redC, d);
                    }

                }
                // 오른쪽
                else if (d == 3 && p.redR == p.blueR) {
                    // 더 오른쪽이 빨간공일때
                    if (p.redC > p.blueC) {
                        // 빨간공 굴리기
                        red = roll(p.redR, p.redC, d);
                        blue = roll(p.blueR, p.blueC, d);
                    } else {
                        // 파란공 굴리기
                        blue = roll(p.blueR, p.blueC, d);
                        red = roll(p.redR, p.redC, d);
                    }
                } else {
                    red = roll(p.redR, p.redC, d);
                    blue = roll(p.blueR, p.blueC, d);
                }

                map[red.r][red.c] = '.';
                map[blue.r][blue.c] = '.';
                map[targetR][targetC] = 'O';

                // 파란색이 홀에 들어갔으면 패스
                if (blue.r == targetR && blue.c == targetC) {
                    continue;
                }

                if (red.r == targetR && red.c == targetC) {
                    return 1;
                }

                // 방문체크 후 큐에 넣기
                if (p.cnt < 10 && !v[red.r][red.c][blue.r][blue.c]) {
                    q.offer(new Bead(red.r, red.c, blue.r, blue.c, p.cnt + 1));
                }
            }
        }
        return 0;
    }

    private static Point roll(int r, int c, int d) {

        while (true) {
            if (map[r + dx[d]][c + dy[d]] == '#' || map[r + dx[d]][c + dy[d]] == 'x') {
                break;
            }

            r += dx[d];
            c += dy[d];

            if (map[r][c] == 'O') {
                return new Point(r, c);
            }
        }
        map[r][c] = 'x';
        return new Point(r, c);
    }

    private static Bead findStartLoc() {
        boolean findRed = false, findBlue = false;
        int redR = 0, redC = 0, blueR = 0, blueC = 0;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] == 'O') {
                    targetR = i;
                    targetC = j;
                }
            }
        }
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (!findRed) {
                    if (map[i][j] == 'R') {
                        redR = i;
                        redC = j;
                        findRed = true;
                    }
                }
                if (!findBlue) {
                    if (map[i][j] == 'B') {
                        blueR = i;
                        blueC = j;
                        findBlue = true;
                    }
                }
                if (findRed && findBlue) {
                    map[redR][redC] = '.';
                    map[blueR][blueC] = '.';

                    return new Bead(redR, redC, blueR, blueC, 1);
                }

            }
        }
        return null;
    }

    public static class Bead {
        int redR;
        int redC;
        int blueR;
        int blueC;
        int cnt;

        public Bead(int redR, int redC, int blueR, int blueC, int cnt) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.cnt = cnt;
        }

    }

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
