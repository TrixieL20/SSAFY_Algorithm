class Solution {
    static final int MAX_USAGE = 5;
    
    static final int DIA = 0;
    static final int IRON = 1;
    static final int STONE = 2;
    
    static int minFatigue = Integer.MAX_VALUE;
    
    int pickMineral(String mineral, final int pick)
    {
        switch(mineral)
        {
            case "diamond":
            {
                if(pick == DIA)
                {
                    return 1;
                }
                else if(pick == IRON)
                {
                    return 5;
                }
                else
                {
                    return 25;
                }
            }
            case "iron":
            {
                if(pick == DIA || pick == IRON)
                {
                    return 1;
                }
                else
                {
                    return 5;
                }
            }
        }
        //Stone 캘 땐 피로도 무조건 1
        return 1;
    }
    
    void dfs(int[] picks, String[] minerals, int idx, int fatigue)
    {
        //곡괭이 사용 여부 체크
        boolean usedPick = false;
        
        for(int i = 0; i < picks.length; i++)
        {
            if(picks[i] == 0)
            {
                continue;
            }
            if(!usedPick)
            {
                usedPick = true;
            }
            
            picks[i]--;
            int nextFatigue = fatigue;
            for(int usage = 0; (usage < MAX_USAGE) && ((idx + usage) < minerals.length); usage++)
            {
                nextFatigue += pickMineral(minerals[idx + usage], i);
            }
            if(MAX_USAGE + idx >= minerals.length)
            {
                minFatigue = Math.min(nextFatigue, minFatigue);
                picks[i]++;
            }
            else
            {
                dfs(picks, minerals, idx + MAX_USAGE, nextFatigue);
                picks[i]++;
            }
        }
        
        //곡괭이 한 번도 안 사용했으면, 곡괭이가 없는 것이므로 피로도 비교
        if(!usedPick)
        {
            minFatigue = Math.min(fatigue, minFatigue);
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        minFatigue = Integer.MAX_VALUE;
        
        dfs(picks, minerals, 0, 0);
        
        return minFatigue;
    }
}
