import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    // 행, 열, 메워야 하는 깊이 저장
	static class Node {
		int row;
		int col;
		int depth;
		
		Node(int row, int col, int depth){
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
	
	static int[][] dist;
	
	public static void findRoute(int[][] roadMap, int n) {
		Node start = new Node(0, 0, 0);
		dist[0][0] = 0;
        // priorityqueue로 깊이 낮은 것 기준으로 꺼냄
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.depth - b.depth);
		
		int[] dir_row = {0, 0, 1, -1};
		int[] dir_col = {1, -1, 0, 0};
		
		pq.offer(start);
		while(!pq.isEmpty()) {
			 Node cur = pq.poll(); 
			 for (int i = 0; i < 4; i++) {
				 int row = cur.row + dir_row[i];
				 int col = cur.col + dir_col[i];
				 
                 // 다음 이동 가능한 지 체크 
				 if(row >= 0 && row < n && col >= 0 && col < n) {
					 int depth = cur.depth + roadMap[row][col]; 
                     // 계산된 depth가 여러 경로마다 dist로 저장해 온 해당 위치까지의 depth합 중 최소보다 작은지 비교 -> 작으면 갱신
					 if(depth < dist[row][col]) {
						 pq.offer(new Node(row, col, depth));
						 dist[row][col] = depth;
					 }
				 }
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			int n = Integer.parseInt(br.readLine());
			int[][] roadMap = new int[n][n];
			
			dist = new int[n][n];

			for(int i = 0; i < n; i++) {
			    Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			for(int row = 0; row < n; row++) {
				String[] s = br.readLine().split("");
				for(int col = 0; col < n; col++) {
					roadMap[row][col] = Integer.parseInt(s[col]);
				}
			}
			
			findRoute(roadMap, n);
			
			sb.append(dist[n-1][n-1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}