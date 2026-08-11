import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution
{	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int x = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			if(x == 1) sb.append(0);
			else if(x == 0) sb.append(1);
			else if(x % 2 == 0) {
				for(int j = 0; j < x / 2; j++) {
					sb.append(8);
				}
			}
			else if(x % 2 == 1) {
				sb.append(4);
				for(int j = 0; j < x / 2; j++) {
					sb.append(8);
				}
			}
			System.out.println(sb);
		}
	}
}
