import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {

    static int count;
    static int max;
    static int[] arr;
    static Set<String>[] visited;

    public static void dfs(int depth) {

        // 정해진 횟수만큼 교환 완료
        if (depth == count) {
            max = Math.max(max, Integer.parseInt(toNumberString()));
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {

                // 교환
                swap(i, j);

                String numberString = toNumberString();

                // depth + 1에서 이미 만들어본 숫자인지 확인
                if (!visited[depth + 1].contains(numberString)) {
                    visited[depth + 1].add(numberString);
                    dfs(depth + 1);
                }

                // 원상복구
                swap(i, j);
            }
        }
    }

    public static String toNumberString() {
        StringBuilder sb = new StringBuilder();

        for (int num : arr) {
            sb.append(num);
        }

        return sb.toString();
    }

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {

            String[] input = br.readLine().split(" ");

            // 숫자판
            String number = input[0];

            // 교환 횟수
            count = Integer.parseInt(input[1]);

            // 숫자판 배열
            arr = new int[number.length()];

            for (int i = 0; i < number.length(); i++) {
                arr[i] = number.charAt(i) - '0';
            }

            max = Integer.MIN_VALUE;

            // 교환 횟수별 방문 상태
            visited = new HashSet[count + 1];

            for (int i = 0; i <= count; i++) {
                visited[i] = new HashSet<>();
            }

            // 초기 상태
            visited[0].add(number);

            dfs(0);

            sb.append("#" )
                    .append(testCase)
                    .append(" ")
                    .append(max)
                    .append("\n");
        }

        System.out.print(sb);
    }
}
