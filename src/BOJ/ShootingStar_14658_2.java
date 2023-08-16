package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShootingStar_14658_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        st.nextToken();
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Point[] points = new Point[K];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            points[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int max = 0, cnt = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                for (int trev = 0; trev < K; trev++) {
                    if (points[trev].x >= points[i].x && points[trev].x <= points[i].x + L &&
                            points[trev].y >= points[j].y && points[trev].y <= points[j].y + L) {
                        cnt++;
                    }
                }
                max = Math.max(max, cnt);
                cnt = 0;
            }
        }
        System.out.println(K - max);
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
