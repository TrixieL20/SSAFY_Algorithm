
import java.util.*;

public class test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int k = 0; k < tc; k++) {
			int n = sc.nextInt();
			int[][] ary = new int[n][2];

			for (int i = 0; i < n; i++) {
				int d = sc.nextInt();
				int t = sc.nextInt();

				ary[i][0] = d;
				ary[i][1] = t;
			}
			Arrays.sort(ary, (a, b) -> a[1] - b[1]);

			int max_start_time = Integer.MAX_VALUE;

			int task_times = 0;
			for (int i = 0; i < ary.length; i++) {
				task_times += ary[i][0];

				if (ary[i][1] - task_times < max_start_time) {
					max_start_time = ary[i][1] - task_times;
				}
			}

			// n^2 시간복잡도나와서 폐기
			// int max_start_time = ary[0][1]+1-ary[0][0];
			// int current_time = max_start_time;
			//
			//
			// for(int i=0; i<ary.length; i++) {
			//
			// if(current_time + ary[i][0] <= ary[i][1]+1) {
			// current_time += ary[i][0];
			//
			// continue;
			// }else {
			// i = -1;
			// max_start_time--;
			// current_time = max_start_time;
			// }
			// }

			System.out.println(max_start_time);
		}

	}

}

// 처음에는 첫 마감이 젤 빠른 애 기준으로 max_start_time(가장 많이 쉴 수있는 시간)을 구하여 그 시간으로 부터 ary를 돌려
// 작업 불가시 하나씩 max_start_time을 -1 하는걸 반복하여 끝까지 돌면 max_start_time을 출력했다 (백트래킹?)
// max_start_time은 t-d+1 로 얻은 거라 문제에서 d,t 는 10^9 까지 커질수 있어서 .. 시간복잡도가 n^2보다
// 커질수있엇다.

// 수정한 풀이는 문제를 다른관점으로 봐야한다
// 언제 시작했느냐는 생각하지않고, 마감시간순으로 정렬한뒤, 변수에 (코드에서 task_times) task[i]의 수행시간 더하고
// task[i] 마감시간에서 task_times을 뺀다. 이 뺸값중 가장 작은것을 구하면 모든 작업이 가능한 최소 시작시간이다
// 가장 작은 텀을 쉰다고 생각하는 발상의전환? 인듯