package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LetsGoTrip_1976 {
    static int N, M; // 도시의 수, 여행 계획에 속한 도시들의 수
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        initP();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                // 버림
                st.nextToken();
            }
            for (int j = i + 1; j <= N; j++) {
                if(Integer.parseInt(st.nextToken())==1) {
                    union(i, j);
                }
            }
        }

        // 여행 ㄱㄱ
        st = new StringTokenizer(br.readLine());

        int start = find(Integer.parseInt(st.nextToken()));

        while (st.hasMoreTokens()) {
            if (start != find(Integer.parseInt(st.nextToken()))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int find(int x) {
        if (p[x] == x) // 배열 인덱스와 값이 같다면 해당 값 리턴
            return x;
        return p[x] = find(p[x]); // 배열의 값을 인덱스로 갖는 값 리턴
    }

    private static void initP() {
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x < y) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
    }

}
