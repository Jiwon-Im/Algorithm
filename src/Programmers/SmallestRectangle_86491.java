package Programmers;

public class SmallestRectangle_86491 {
    static int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

    public static void main(String[] args) {
        int x = 1, y = 1;
        // 가장 긴 변 찾기 & 정렬
        // 긴 변은 [1]에서 찾기
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] > sizes[i][1]) {
                swap(i);
            }
            x = Math.max(x, sizes[i][1]); // 긴 변은 [1]에서 찾기
            y = Math.max(y, sizes[i][0]); // 짧은 변은 [0]에서 가장 큰 값
        }
        System.out.println(x * y);
    }

    static private void swap(int i) {
        int temp = sizes[i][0];
        sizes[i][0] = sizes[i][1];
        sizes[i][1] = temp;
    }

}
