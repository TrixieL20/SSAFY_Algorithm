import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static final int[] dx = new int[] {0, 1, 0, -1};
	static final int[] dy = new int[] {1, 0, -1, 0};	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int answer = 0;
			int[][] times = new int[N][N];
			boolean[][] isVisited = new boolean[N][N];
			Queue<int[]> q = new ArrayDeque<>();
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}//end of init map
			
			q.offer(new int[] {0, 0});
			isVisited[0][0] = true;
			
			while(!q.isEmpty()) {
				int[] pos = q.poll();
				
				for(int i = 0; i < dx.length; i++)
				{
					int nx = pos[0] + dx[i];
					int ny = pos[1] + dy[i];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					if(isVisited[nx][ny] == false || times[pos[0]][pos[1]] + map[nx][ny] < times[nx][ny]) {
						times[nx][ny] = times[pos[0]][pos[1]] + map[nx][ny];
						q.offer(new int[] {nx, ny});
						isVisited[nx][ny] = true;
					}
				}
			}
			
			answer = times[N - 1][N - 1];
			
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of for
		System.out.print(sb);
	}
}                                                
