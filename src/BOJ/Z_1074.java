package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z_1074 {
    static int N, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dfs(0, 0, N / 2, 0); // r : 좌상단 좌표 row, c : 좌상단 좌표 col, len : 한변의 길이 len
    }
    // 현재 구역을 4등분으로 나누었을 때 R,C가 어느 구역에 위치하는가를 기준으로 dfs
    private static void dfs(int r, int c, int len, int sum) {

        if (len == 0) { // 탐색할 구역의 변의 길이가 1이면 R,C 도착!
            System.out.println(sum);
            return;
        }

        // 상단
        if (R < r + len) {
            if (C < c + len) {  // 왼쪽
                dfs(r, c, len / 2, sum);        // 왼쪽 상단 좌표, 다음 Z변의 길이, 이번 Z로 오기까지 지나온 칸의 개수
            } else {            // 오른쪽
                dfs(r, c + len, len / 2, sum + len * len);                  // r, c 이번 Z의 좌상단 좌표
            }
        }
        // 하단
        else {
            if (C < c + len) {  // 왼쪽
                dfs(r + len, c, len / 2, sum + len * len * 2);              // len : 길이는 반씩 줄어듦
            } else {            // 오른쪽
                dfs(r + len, c + len, len / 2, sum + len * len * 3);     // sum : 4구역 왼쪽 상단 기준이므로 1,2,3구역 넓이만큼 더하기
            }
        }
    }
}
