package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ElectricWire_2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] wire = new int[N][2];
        int[] dp = new int[N+1];

        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            wire[i][0] = Integer.parseInt(st.nextToken());
            wire[i][1] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        Arrays.sort(wire, Comparator.comparingInt(o -> o[0]));

        // 가장 긴 증가하는 부분수열 찾기
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(wire[i][1]>wire[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = 0;
        for(int x : dp) {
            max = Math.max(max, x) ;
        }
        System.out.println(N-max);
    }
}
