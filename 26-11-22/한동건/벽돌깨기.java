import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * 
 * 접근:
 * 음, 일단 구슬 개수 N, 너비,높이 W, H의 최대 값이 4, 12, 15로 좀 작음, 그냥 시뮬로 풀라는거 같음
 *  
 * 구슬의 떨어지는 위치 정해야하고,  연쇄로 부숴지는 로직 + 땅에 떨구는 로직 ...등 짜야함
 * 
 * 벽돌이 가장 적게 남는 개수를 구해야하는데. 12칸 전부 하나씩 다 떨궈서 모든 경우의수중 작은거 구하기?
 * 12*4 번 시행에 각 시행당 연쇄 폭파 로직.. 이면은 낫배드?   
 * 
 */
public class SWEA_5656_벽돌깨기_Dx {

	static int N;
	static int W;
	static int H;
	static int blockCnt;
	static int[][] OriginalGrid;

	static int brokenBlockCnt;

	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String[] infos = br.readLine().split(" ");

			N = Integer.parseInt(infos[0]);
			W = Integer.parseInt(infos[1]);
			H = Integer.parseInt(infos[2]);

			OriginalGrid = new int[H][W];

			blockCnt = 0;
			for (int j = 0; j < H; j++) {
				String[] line = br.readLine().split(" ");
				for (int i = 0; i < W; i++) {
					OriginalGrid[j][i] = Integer.parseInt(line[i]);

					if (OriginalGrid[j][i] != 0) {
						blockCnt++;
					}
				}
			}

			res = Integer.MAX_VALUE;

			dfs(0, blockCnt, OriginalGrid);

			System.out.println("#" + tc + " " + res);
		} // end of tc

	}// end of main

	private static void dfs(int ballCnt, int blockCnt, int[][] grid) {

		int[][] curGrid = new int[H][W];

		// 사용한 공 카운트
		if (ballCnt == N) {
			res = Math.min(blockCnt, res);

			return;
		}

		// 공 떨굴 자리 선택
		for (int w = 0; w < W; w++) {
			for (int i = 0; i < H; i++) {
				curGrid[i] = grid[i].clone();
			}
			int h = dropBall(w, curGrid);

//			System.out.println(w+ ","+h);

			brokenBlockCnt = 0;
			crush(w, h, curGrid);

//			for(int i=0; i<H;i++) {
//				System.out.println(Arrays.toString(curGrid[i]));
//			}
//			System.out.println("-------------");

//			int[][] afterCrushedGrid = new int[H][W];
//			afterCrushedGrid = 
			fallBlock(curGrid);

			dfs(ballCnt + 1, blockCnt - brokenBlockCnt, curGrid);

		}

	}

	private static void fallBlock(int[][] grid) {

		Deque<Integer> tmpQue = new ArrayDeque<>();

		// 열의 밑에서 부터 탐색
		for (int w = 0; w < W; w++) {
			for (int h = H - 1; h >= 0; h--) {

				// 블럭 만나면 큐에저장
				if (grid[h][w] != 0) {
					tmpQue.add(grid[h][w]);
				}

				grid[h][w] = 0;
			}
			
			int idx = H - 1;
			while (!tmpQue.isEmpty()) {

				// 큐에 넣은거 차례대로 넣기
				grid[idx--][w] = tmpQue.pollFirst();

			}
			
		}
		
		
		

	}

	private static void crush(int w, int h, int[][] grid) {
		Deque<int[]> que = new ArrayDeque<>();

		// {x,y}
		que.offer(new int[] { w, h });

		while (!que.isEmpty()) {
			int[] curPos = que.pollFirst();

			int curX = curPos[0];
			int curY = curPos[1];

			// 범위 밖 좌표가 큐에 들어가면 continue
			if (!isInRange(curX, curY))
				continue;

			int power = grid[curY][curX];

			// 원래 빈 공간이 큐에 들어간거니까 continue
			if (power == 0)
				continue;

			// 이번 좌표 벽돌 삭제
			grid[curY][curX] = 0;
			brokenBlockCnt++;

			for (int i = 1; i < power; i++) {
				que.add(new int[] { curX + i, curY });
				que.add(new int[] { curX - i, curY });
				que.add(new int[] { curX, curY + i });
				que.add(new int[] { curX, curY - i }); 
			}

		}

	}

	private static boolean isInRange(int x, int y) {

		if (x >= 0 && x < W && y >= 0 && y < H) {
			return true;
		}

		return false;
	}

	private static int dropBall(int W, int[][] grid) {

		// 이번 W에서 맨위에있는 좌표
		for (int j = 0; j < H; j++) {
			if (grid[j][W] != 0) {
				return j;
			}
		}

		return 0;

	}

}// end of class
