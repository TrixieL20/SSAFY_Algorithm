
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static String[] map;
	
	public static int moveLadder(int v) {
		
		// 0우 1좌 2하
		int dir = 0;
		
		int currX = 0;
		int currY = v;
		
		while(true) {
			
			if(map[currX].charAt(currY) == '2') return v; // 종료지점을 발견하면 v리턴
			if(currX == 99) return -1; // 끝까지 탐색 했지만 목표 지점이 아닐 때 -1 리턴
			
			int ny = currY + 1;
			
			// 왼쪽으로 이동
			// 이전에 오른쪽으로 이동했다면 좌측으로 이동 불가
			if(dir != 1 && ny < 100 && map[currX].charAt(ny) == '1') {
				dir = 0;
				currY = ny;
				continue;
			}
			
			// 오른쪽으로 이동
			// 이전에 왼쪽으로 이동했다면 우측으로 이동 불가
			ny = currY - 1;
			if(dir != 0 && ny >= 0 && map[currX].charAt(ny) == '1') {
				dir = 1;
				currY = ny;
				continue;
			}
			
			// 좌우 모두 이동할 수 없다면 아래로 계속 이동
			if(map[currX + 1].charAt(currY) == '1' || map[currX + 1].charAt(currY) == '2') {
				dir = 2;
				currX += 1;
				continue;
			}
			
			return -1;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int T = Integer.parseInt(br.readLine());
			
			map = new String[100];
			for(int i = 0; i < 100; i++) {
				map[i] = br.readLine().replace(" ", "");
			}

			
			sb.append("#" + test_case + " ");
			
			for(int i = 0; i < 100; i++) {
				if(map[0].charAt(i) == '1') {		
					if(moveLadder(i) != -1) {
						sb.append(i).append("\n");
					}
				}
			}
			
		}
		System.out.println(sb);
	}
}
