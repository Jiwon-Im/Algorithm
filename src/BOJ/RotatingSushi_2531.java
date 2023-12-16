package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2531
public class RotatingSushi_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시의 수
        int D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int C = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] menu = new int[3001];
        int[] sushi = new int[N];

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        // 초기화, 첫번째 세트
        for (int i = 0; i < K; i++) {
            if (menu[sushi[i]] == 0) { // 새로운 접시
                cnt++;
            }
            menu[sushi[i]]++;
        }
        int ans = cnt;
        // 쿠폰 적용
        if (menu[C] == 0) {
            ans = Math.max(ans, cnt + 1);
        }

        // 슬라이딩 윈도우
        int j = K;
        for (int i = 0; i < N; i++) {
            // 첫번째 접시 빼기
            menu[sushi[i]]--;
            if (menu[sushi[i]] == 0) { // 한종류만 있던 접시라면 cnt 빼기
                cnt--;
            }
            // 마지막 접시 더하기
            menu[sushi[j]]++;
            if (menu[sushi[j]] == 1) { // 새로운 접시라면 cnt 더하기
                cnt++;
            }
            // 쿠폰 적용
            if (menu[C] == 0) {
                ans = Math.max(ans, cnt + 1);
            } else {
                ans = Math.max(ans, cnt);
            }
            j = (j + 1) % N;
        }
        // ans : 먹을 수 있는 초밥의 가짓수의 최댓값
        System.out.println(ans);
    }
}
