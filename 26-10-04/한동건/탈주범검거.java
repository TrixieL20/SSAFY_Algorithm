import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1953_탈주범검거_Dx {

	static int R;
	static int C;
	static int L;

	static int N;
	static int M;
	
	static int res;
	
	static int[][] grid;
	static boolean[][] visited;
	
	
	// 북 동 남 서 (시계방향)
	static int[] dys = { -1, 0, 1, 0 };
	static int[] dxs = { 0, 1, 0, -1 };

	// 현재 파이프에서 가는 방향의 파이프가 연결 가능한거 모음
	static int[][] connectablePipe = {
			{1,2,5,6},
			{1,3,6,7},
			{1,2,4,7},
			{1,3,4,5}
			};
	
	// 현재 파이프가 나아갈수 있는 방향 저장
	static boolean[][] canGoDir = {
			{false},
			{true,true,true,true},
			{true,false,true,false},
			{false,true,false,true},
			{true,true,false,false},
			{false,true,true,false},
			{false,false,true,true},
			{true,false,false,true},
			
			
	};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] infos = br.readLine().split(" ");

			N = Integer.parseInt(infos[0]);
			M = Integer.parseInt(infos[1]);

			R = Integer.parseInt(infos[2]);
			C = Integer.parseInt(infos[3]);

			L = Integer.parseInt(infos[4]);

			grid = new int[N][M];
			visited = new boolean[N][M];
			
			for (int j = 0; j < N; j++) {
				String[] row = br.readLine().split(" ");
				for (int i = 0; i < M; i++) {
					grid[j][i] = Integer.parseInt(row[i]);
				}
			}
			res = 0;
			bfs();
			
			System.out.println("#"+tc+" "+res);
		} // end of tc

	}// end of main

	public static void bfs() {

		Deque<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { R, C, 1});
		visited[R][C]=true;
		res+=1;
		
		while (!q.isEmpty()) {

			int[] polled = q.poll();

			int curY = polled[0];
			int curX = polled[1];
			int curL = polled[2];
			
			// L시간까지 갈수잇느거 다구햇으면 스킵하고 다음 큐로
			if(curL == L)continue;			
		
			
			
			int pipeShape = grid[curY][curX];

			for (int i = 0; i < 4; i++) {
				
				// 파이프 모양이 갈 수 없는 방향이면 스킵
				if(!canGoDir[pipeShape][i])continue;
				
				int nx = curX + dxs[i];
				int ny = curY + dys[i];
				
				// 범위 밖이거나, 파이프 아니거나, 방문했다면 스킵
				if(nx<0 || ny <0 || nx >=M || ny >=N || grid[ny][nx]==0 || visited[ny][nx]) continue;
				
				
				
				boolean flag = false;
				
				// 다음 파이프가 현재 파이프와 연결 가능한지
				for(int pipe : connectablePipe[i]) {
					if(pipe == grid[ny][nx]) {
						flag= true;
						break;
					}
				}
				
				// 다음 파이프모양이 연결 못하면 continue
				if(!flag)continue;
				
				// 다 가능하다면 큐에 넣기
				q.offer(new int[] {ny,nx,curL+1});
				res++;
				visited[ny][nx] = true;
				
		

			}

		}

	}
}// end of class
