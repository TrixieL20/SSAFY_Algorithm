import java.util.*;
class Solution {
    static int maxDiff;
    static int[] answer;
    public int[] solution(int n, int[] info) {
        int[] rInfo = new int[11];
        maxDiff = -1;
        answer = new int[]{-1};
        
        combi(info, rInfo, 0, n, 0, 0);
        return answer;
    }
    
    void combi(final int[] aInfo, int[] rInfo, int idx, final int n, int cnt, int aScore) {
        if(n <= cnt)
        {
            aScore = checkScore(aInfo, rInfo, true);
            int rScore = checkScore(rInfo, aInfo, false);
            int diff = rScore - aScore;
            if(diff <= 0)
            {
                return;
            }
            
            if(maxDiff < diff)
            {
                maxDiff = diff;
                answer = Arrays.copyOf(rInfo, 11);
            }
            
            else if(maxDiff == diff)
            {
                for(int i = rInfo.length - 1; i >= 0; i--)
                {
                    if(rInfo[i] < answer[i])
                    {
                        break;
                    }
                    if(rInfo[i] > answer[i])
                    {
                        answer = Arrays.copyOf(rInfo, 11);
                        break;
                    }
                }
            }
            
            return;
        }
        
        if(idx >= rInfo.length)
        {
            return;
        }
        
        if(aScore >= 28) //점수 반 이상이 넘어버리면 라이언이 절대 못 넘기므로 리턴
        {
            return;
        }
        
        if(idx == rInfo.length - 1) //0에 남은 화살 다 쓰기
        {
            rInfo[idx] = n - cnt;
            combi(aInfo, rInfo, idx + 1, n, n, aScore);
            rInfo[idx] = 0;
            return;
        }
        
        if(aInfo[idx] > 0 && n - cnt <= aInfo[idx]) //어피치 화살보다 남은 화살 적은 경우, 안 쏘기
        {
            combi(aInfo, rInfo, idx + 1, n, cnt, aScore + 10 - idx);
            return;
        }
        
        int offset = 1 + aInfo[idx];
        rInfo[idx] = offset;
        combi(aInfo, rInfo, idx + 1, n, cnt + offset, aScore);
        rInfo[idx] = 0;
        combi(aInfo, rInfo, idx + 1, n, cnt, aScore + 10 - idx);
    }
    
    int checkScore(final int[] info, final int[] compInfo, boolean isApeach)
    {
        int ret = 0;
        for(int i = 0; i < info.length; i++)
        {
            if(info[i] > compInfo[i])
            {
                ret += 10 - i;
            }
            else if(info[i] > 0 && isApeach && info[i] == compInfo[i])
            {
                ret += 10 - i;
            }
        }
        return ret;
        
    }
}
