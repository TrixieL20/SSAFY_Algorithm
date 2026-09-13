import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Solution
{
    static int maxScore;
     // 재료별 맛 점수와 칼로리의 int[] 배열을 저장하는 리스트
    static List<int[]> ingredients;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{   
            sb.append("#").append(test_case).append(" ");
            String[] s = br.readLine().split(" ");
            // 재료의 수
            int n = Integer.parseInt(s[0]);
            // 제한 칼로리
            int limit = Integer.parseInt(s[1]);
            
           ingredients = new ArrayList<>();
            for(int i = 0; i < n; i++){
                s = br.readLine().split(" ");
                int score = Integer.parseInt(s[0]);
                int calory = Integer.parseInt(s[1]);
                ingredients.add(new int[]{score, calory});
            }
            maxScore = 0;
            dfs(0, n, 0, 0, limit);
            sb.append(maxScore).append("\n");
		}
        System.out.println(sb.toString());
	}

    static void dfs(int index, int n, int calories, int scores, int limit){
        if(index == n){
            maxScore = Math.max(maxScore, scores);
            return;
        }
        dfs(index + 1, n, calories, scores, limit);
        if(calories + ingredients.get(index)[1] <= limit){
            dfs(index+1, n, calories+ingredients.get(index)[1], 
            scores+ingredients.get(index)[0], limit);
        }
    }
}