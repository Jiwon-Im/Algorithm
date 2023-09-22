package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Chairman_2660 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        init();
        floyd();

        int candidateMaxDistance = N;   // 최댓값 초기화
        int cnt = 0;
        ArrayList<Integer> candidates = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int minDistance = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                minDistance = Math.max(minDistance, map[i][j]);
            }

            // 후보 불가능 없음
            if (minDistance > candidateMaxDistance) {
                continue;
            }

            // 후보 가능
            // 더 적은 거리를 가진 새로운 후보
            if (minDistance < candidateMaxDistance) {
                cnt = 1;
                candidateMaxDistance = minDistance;
                candidates = new ArrayList<>();
            }
            // 같은 거리를 가진 후보 추가
            else {
                cnt++;
            }
            // 후보 추가
            candidates.add(i);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(candidateMaxDistance).append(" ").append(cnt).append(" ").append("\n");
        for (int candidate : candidates) {
            sb.append(candidate).append(" ");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        // 플로이드 와샬 거리 구하기
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    // 새로운 관계
                    if (map[i][j] == 0) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                    // 더 짧은 거리로
                    else {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = N;
            }
        }

        // 친구관계 거리 : 1
        int a, b;
        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a < 0) {
                break;
            }

            map[a][b] = 1;
            map[b][a] = 1;
        }
    }
}
