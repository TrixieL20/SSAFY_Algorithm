import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/*
 * 접근:
 * 
 * 음 BFS로 접근하고
 * 해당 격자까지 걸리는 최소 시간을 저장하는 격자를 또 만들고 
 * 격자를 이동할때 이 최소시간보다 작을때만 push하기?
 * 
 */
public class SWEA_1249_보급로_D4 {
	static int[] dys = { 1, -1, 0, 0 };
	static int[] dxs = { 0, 0, 1, -1 };

	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			// 초기 grid 설정
			int[][] grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					grid[i][j] = line.charAt(j) - '0';
				}
			}

			// 각 격자에 도달하기까지 최소시간 저장하는 grid
			int[][] minTimeGrid = new int[N][N];
			// 최대시간으로 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(minTimeGrid[i], Integer.MAX_VALUE);
			}
			
			Deque<int[]> que = new ArrayDeque<>();

			// 초기 설정
			{
				minTimeGrid[0][0] = 0;
				minTimeGrid[0][1] = grid[0][1];
				minTimeGrid[1][0] = grid[1][0];

				que.offer(new int[] { 0, 1 });
				que.offer(new int[] { 1, 0 });
			}

			while (!que.isEmpty()) {
				int[] curPos = que.poll();
				
//				System.out.println(curPos[0]+", "+curPos[1]+":"+minTimeGrid[curPos[1]][curPos[0]]);
				int nx, ny;

				for (int i = 0; i < 4; i++) {

					nx = curPos[0] + dxs[i];
					ny = curPos[1] + dys[i];

					// 다음 격자가 범위 내 아니면 건너뛰기
					if (!isInRange(nx, ny))
						continue;

					// ( 다음 격자 최소시간 <= 다음 격자 걸리는시간 + 현재 격자 최소시간) 이면 건너뛰기
					if (minTimeGrid[ny][nx] <= minTimeGrid[curPos[1]][curPos[0]] + grid[ny][nx])
						continue;
					
					minTimeGrid[ny][nx] = minTimeGrid[curPos[1]][curPos[0]] + grid[ny][nx];
					
					que.add(new int[] { nx, ny });

				}
			}
			
			System.out.println("#"+tc+" "+minTimeGrid[N-1][N-1]);

		} // end of testcase
		
	}// end of main

	static public boolean isInRange(int x, int y) {
		// 범위 내 인가?
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return false;
		}

		return true;
	}

}// end of class
