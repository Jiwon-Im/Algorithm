package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LetsGoTrip2_1976 {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        br.readLine();
        
        // 루트 배열 본인으로 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                st.nextToken();
            }
            for (int j = i + 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    // 합하기
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        // 여행 루트가 모두 같으면 여행 가능
        int p = find(Integer.parseInt(st.nextToken()));

        while (st.hasMoreTokens()) {
            if (find(Integer.parseInt(st.nextToken())) != p) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x <= y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private static int find(int i) {
        // 루트 찾기
        if (i == parent[i]) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
}
