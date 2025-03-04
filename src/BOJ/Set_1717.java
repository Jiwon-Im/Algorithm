package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Set_1717 {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // {0}, ... , {n}
        M = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
        parent = new int[N + 1];
        initP();

        for (int m = 0; m < M; m++) {
            // 각 연산이 주어짐
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (flag == 1) { // 두 원소가 같은 집합인가?
                if(find(a)==find(b)){
                    sb.append("yes").append("\n");
                }else{
                    sb.append("no").append("\n");
                }

            } else { // 합집합
                a = find(a);
                b = find(b);

                if (a < b) {
                    parent[b] = a;
                } else {
                    parent[a] = b;
                }
            }
        }
        System.out.println(sb);

    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void initP() {
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
    }
}
