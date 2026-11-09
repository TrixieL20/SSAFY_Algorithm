
import java.io.BufferedReader;
import java.io.InputStreamReader;

class BC {
	int x;
	int y;
	int c;
	int p;

	public BC(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class Solution {
	static int m;
	static int a;
	static int[] userA;
	static int[] userB;
	static BC[] bc;

	static final int[] mx = { 0, 0, 1, 0, -1 };
	static final int[] my = { 0, -1, 0, 1, 0 };

	static boolean[] reachA;
	static boolean[] reachB;

	public static int bfs() {
		int ax = 1, ay = 1;
		int bx = 10, by = 10;
		int total = 0;

		for (int i = 0; i <= m; i++) {
			// 0초에는 이동하지 않음
			if (i > 0) {
				ax += mx[userA[i - 1]];
				ay += my[userA[i - 1]];
				bx += mx[userB[i - 1]];
				by += my[userB[i - 1]];
			}

			// 충전 범위 체크
			checkIsReach(ax, ay, bx, by);

			int maxCurr = 0;

			for (int j = 0; j < a; j++) {
				for (int k = 0; k < a; k++) {
					int sumA = 0;
					int sumB = 0;

					if (reachA[j])
						sumA = bc[j].p;
					if (reachB[k])
						sumB = bc[k].p;

					// 같은 충전소면 전력 반으로 나누기
					if (j == k && reachA[j] && reachB[k]) {
						sumA /= 2;
						sumB /= 2;
					}

					maxCurr = Math.max(maxCurr, sumA + sumB);
				}
			}
			
			total += maxCurr;
		}

		return total;
	}

	public static void checkIsReach(int x1, int y1, int x2, int y2) {
		reachA = new boolean[a];
		reachB = new boolean[a];

		for (int i = 0; i < a; i++) {
			if (Math.abs(x1 - bc[i].x) + Math.abs(y1 - bc[i].y) <= bc[i].c) {
				reachA[i] = true;
			}

			if (Math.abs(x2 - bc[i].x) + Math.abs(y2 - bc[i].y) <= bc[i].c) {
				reachB[i] = true;
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String[] input = br.readLine().split(" ");
			m = Integer.parseInt(input[0]);
			a = Integer.parseInt(input[1]);

			input = br.readLine().split(" ");
			userA = new int[m];
			for (int i = 0; i < input.length; i++) {
				userA[i] = Integer.parseInt(input[i]);
			}

			input = br.readLine().split(" ");
			userB = new int[m];
			for (int i = 0; i < input.length; i++) {
				userB[i] = Integer.parseInt(input[i]);
			}

			bc = new BC[a];

			for (int i = 0; i < a; i++) {
				input = br.readLine().split(" ");
				bc[i] = new BC(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]),
						Integer.parseInt(input[3]));
			}

			int answer = bfs();

			sb.append("#" + test_case + " " + answer + "\n");
		}
		System.out.println(sb);
	}
}
