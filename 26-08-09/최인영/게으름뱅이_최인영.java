/**
 * 26793_게으름뱅이_최인영 문제 풀이
 */

import java.util.*;

public class 게으름뱅이_최인영 {
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            // 과제 개수 N
            int N = sc.nextInt();
            // 과제에 걸리는 시간 / 데드라인 입력 받을 배열 homeworks
            int homeworks[][] = new int[N][2];
            // 게으름뱅이가 숙제 시작해야 하는 최소한의 날짜 start_time
            int start_time;

            for(int hw_count = 0; hw_count < N; hw_count++){
                // i 번째 과제에 걸리는 시간 Dᵢ 는 homeworks 배열 i행 0열에 저장
                homeworks[hw_count][0] = sc.nextInt();
                // i 번째 과제의 데드라인 Tᵢ 는 homeworks 배열 i행 1열에 저장
                homeworks[hw_count][1] = sc.nextInt();
            }

            // homeworks 배열 데드라인이 늦은 순으로 정렬 (tᵢ의 내림차순)
            Arrays.sort(homeworks, (o1, o2) -> Integer.compare(o2[1], o1[1]));

            // start_time -> 최초엔 데드라인 가장 늦은 과제의 마감일로 설정
            start_time = homeworks[0][1];

            // 데드라인 늦은 순으로 정렬해둔 homework 과제들마다 
            // 현재까지 계산된 start_time과 수행할 과제의 데드라인을 비교한 뒤
            // 더 이른 쪽에서 과제에 걸리는 시간을 빼서 저장
            for (int[] hw : homeworks) {
                start_time = (start_time <= hw[1]) ? start_time: hw[1];
                start_time -= hw[0];
            }
            System.out.println(start_time);
		}
	}
}