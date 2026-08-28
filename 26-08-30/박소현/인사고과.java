import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer;
        int n = scores.length;
        List<int[]> list = new ArrayList<int[]>();
        
        int[] whScores = scores[0];
        int whScoreSum = whScores[0] + whScores[1];
        
        for(int i = 1; i < n; i++)
        {
            if(scores[i][0] > whScores[0] && scores[i][1] > whScores[1])
            {
                return -1;
            }
            if(scores[i][0] + scores[i][1] > whScoreSum)
            {
                list.add(scores[i]);
            }
        }
        
        Collections.sort(list, (a, b) ->
         {
            if(a[0] == b[0])
                return a[1] - b[1];
             return a[0] - b[0];
         });
        
        answer = list.size() + 1;
        for(int i = 0; i < list.size(); i++) {
            int[] s1 = list.get(i);
            for(int j = i + 1; j < list.size(); j++)
            {
                int[] s2 = list.get(j);
                if(s1[0] < s2[0] && s1[1] < s2[1])
                {
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
}
