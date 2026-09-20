import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int answer = 0;
	static boolean[][] isVisited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			int[] nums = new int[str.length()];
			for(int i = 0; i < str.length(); i++) {
				nums[i] = str.charAt(i) - '0';
			}
			
			int cnt = Integer.parseInt(st.nextToken());
			answer = -1;
			isVisited = new boolean[1000000][12];
			swapNumbers(nums, cnt);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		
		System.out.print(sb);
	}// end of main
	
	static void swapNumbers(int[] nums, int cnt)
	{
		if(cnt == 0)
		{
			int num = convertNums(nums);
			answer = Math.max(answer, num);
			return;
		}
		
		for(int i = 0; i < nums.length; i++)
		{
			for(int j = i + 1; j < nums.length; j++)
			{
				swap(nums, i, j);
				int num = convertNums(nums);
				if(!isVisited[num][cnt - 1])
				{
					isVisited[num][cnt - 1] = true;
					swapNumbers(nums,  cnt - 1);
					isVisited[num][cnt - 1] = true;
				}
				swap(nums, i, j);
			}
			
		}
		
	}
	
	static void swap(int[] nums, int a, int b)
	{
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
	static int convertNums(int[] nums)
	{
		int ret = nums[0];
		for(int i = 1; i < nums.length; i++)
		{
			ret *= 10;
			ret += nums[i];
		}
		return ret;
	}
}
