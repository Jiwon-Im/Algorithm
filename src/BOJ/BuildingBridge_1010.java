package BOJ;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

public class BuildingBridge_1010 {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        map = new int[30][30];

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            sb.append(find(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int r, int n) {
        if (map[n][r] > 0) return map[n][r];
        if (r == 1) return map[n][r] = n;
        if (r == n) return map[n][r] = 1;

        return map[n][r] = find(r - 1, n - 1) + find(r, n - 1);
    }
}
