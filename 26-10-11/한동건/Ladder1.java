import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



public class Ladder1 {

	static int[][] ladder;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		

		for (int tc = 1; tc <= 10; tc++) {
			int TC = Integer.parseInt(br.readLine());
			int[] destPos = new int[2]; // x,y

			ladder = new int[100][100];
			visited = new boolean[100][100];

			for (int i = 0; i < 100; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(line[j]);

					if (ladder[i][j] == 2) {
						destPos[0] = j; // x
						destPos[1] = i; // y
					}
				}
			}

			Queue<int[]> q = new ArrayDeque<>();

			q.offer(new int[] {destPos[0],destPos[1]-1});
			
//			System.out.println(Arrays.toString(destPos));
			visited[destPos[1]-1][destPos[0]] = true;
			int res = 0;
			
			while (!q.isEmpty()) {
				int[] polled = q.poll();

				int curX = polled[0];
				int curY = polled[1];
				
				if(curY==0) {
					res= curX;
					break;
				}
				
				

				if (checkSide(curX + 1, curY)) {
					q.offer(new int[] { curX + 1, curY });
					visited[curY][curX+1] = true;
					continue;
					
				}
				if (checkSide(curX - 1, curY)) {
					q.offer(new int[] { curX - 1, curY });
					visited[curY][curX-1] = true;
					continue;
					
				}
				
				q.offer(new int[] {curX, curY-1});
				visited[curY-1][curX] = true;
				
				
				}

			
			System.out.println("#"+TC+" "+res);

		} // end of tc

	}// end of main

	public static boolean checkSide(int x, int y) {

		if (x >= 0 && x < 100 && y>=0 && y<100 && ladder[y][x] == 1 && !visited[y][x]) {
			return true;
		}

		return false;
	}

}// end of class
