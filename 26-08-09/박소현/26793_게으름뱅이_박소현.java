import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		int T;
		int N;
		int day;
		int startDay;
		int[] info;
		List<int[]> infos = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			infos.clear();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			for(int i = 0; i < N; i++)
			{
				info = new int[2];
				st = new StringTokenizer(br.readLine(), " ");
				info[0] = Integer.parseInt(st.nextToken());
				info[1] = Integer.parseInt(st.nextToken());
				infos.add(info);
			}
			
			Collections.sort(infos, (a, b)-> b[1] - a[1]);
			
			day = infos.get(0)[1] - infos.get(0)[0];
			for(int i = 1; i < infos.size(); i++)
			{
				info = infos.get(i);
				startDay = info[1] - info[0] + 1;
				if(startDay < day - info[0] + 1)
				{
					day = startDay - 1;
				}
				else
				{
					day = day - info[0];
				}
			}
			
			System.out.println(day);
		}
	}
}
