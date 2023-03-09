package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GaryMandering_17471 {

    static int N;
    static int min = Integer.MAX_VALUE;
    static boolean[] v;
    static int[] population;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        v = new boolean[N + 1];
        adjList = new List[N + 1];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            adjList[i] = new ArrayList<>();
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                adjList[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        subset(0);

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    static private void subset(int s) {
        if (s == N + 1) {
            // 두 구역 모두 인접한 조건을 만족한다면
            if (check()) {
                int sum1 = 0, sum2 = 0;
                for (int i = 1; i <= N; i++) {
                    if (v[i]) {
                        sum1 += population[i];
                    } else {
                        sum2 += population[i];
                    }
                }
                min = Math.min(Math.abs(sum1 - sum2), min);
            }
            return;
        }

        for (int i = s; i <= N; i++) {
            v[i] = true;
            subset(i + 1);
            v[i] = false;
            subset(i + 1);
        }
    }

    // 인접한지 확인하는 함수
    static private boolean check() {

        // group1(true) 체크
        Queue<Integer> que = new LinkedList<>();
        int cnt = 0;
        ArrayList<Integer> set1 = new ArrayList<>();
        ArrayList<Integer> set2 = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (v[i]) {
                set1.add(i);
            } else {
                set2.add(i);
            }
        }

        que.offer(set1.remove(0));
//        while (!que.isEmpty()) {
//            int vertex = que.poll();
//            for (int n : .get(vertex)) {
////                if ()
//            }
//        }

        return true;
    }
}
