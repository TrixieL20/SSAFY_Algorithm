import java.io.*;
import java.util.*;

public class Solution {

    static char[] numbers;
    static int changeCount;
    static int max;

    // 교환 횟수별 방문한 숫자 저장
    static HashSet<String>[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            numbers = st.nextToken().toCharArray();
            changeCount = Integer.parseInt(st.nextToken());

            max = 0;

            // 교환 횟수별 방문 체크
            visited = new HashSet[changeCount + 1];
            for (int i = 0; i <= changeCount; i++) {
                visited[i] = new HashSet<>();
            }

            dfs(0);
            System.out.println("#" + tc + " " + max);
        }
    }


    static void dfs(int count) {
        // 정해진 횟수만큼 교환 완료
        if (count == changeCount) {

            int number = Integer.parseInt(new String(numbers));
            max = Math.max(max, number);
            return;
        }


        // 모든 두 위치 선택
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // 교환
                swap(i, j);
                // 현재 숫자 상태
                String current = new String(numbers);


                // 이 교환 횟수에서 처음 보는 숫자라면
                if (!visited[count + 1].contains(current)) {
                    visited[count + 1].add(current);
                    // 다음 교환
                    dfs(count + 1);
                }


                // 원상복구
                swap(i, j);
            }
        }
    }


    static void swap(int i, int j) {

        char temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}