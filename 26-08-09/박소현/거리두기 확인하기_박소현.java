import java.util.*;

class Solution {
    static final int ROOMSIZE = 5;
    
    //(0, 1) (1, 0) 오른쪽, 아래 한 칸
    //(0, 2) (2, 0) 오른쪽, 아래 두 칸
    //(1, -1) (-1, 1) 아래 왼쪽, 아래 오른쪽 대각선
    static final int[] dx = {0, 1, 0, 2, 1, 1};
    static final int[] dy = {1, 0, 2, 0, -1, 1};
    
    public static boolean isIndexOutOfBound(int idx)
    {
        return idx < 0 | idx >= ROOMSIZE;
    }
    
    public static int checkPlace(final String[] place)
    {
        int ret = 1;
        for(int x = 0; x < place.length; x++)
        {
            for(int y = 0; y < place[x].length(); y++)
            {
                if(place[x].charAt(y) != 'P')
                {
                    continue;
                }
                ret = checkDistancing(place, x, y);
                if(ret == 0)
                {
                    return ret;
                }
            }
        }
        return ret;
    }
    
    public static int checkDistancing(final String[] place, final int x, final int y)
    {        
        for(int k = 0; k < dx.length; k++)
        {
            int nx = x + dx[k], ny = y + dy[k];
            
            if(isIndexOutOfBound(nx) || isIndexOutOfBound(ny))
            {
                continue;
            }
            
            switch(k)
            {
                case 0, 1: //오른쪽, 아래 한 칸
                    {
                        if(place[nx].charAt(ny) == 'P')
                        {
                            return 0;
                        }
                        break;
                    }
                case 2, 3: //오른쪽, 아래 두 칸
                    {
                        if(place[nx].charAt(ny) != 'P')
                            continue;
                        
                        //사람 사이에 파티션 있는지 체크
                        int partitionX = k == 2 ? x : x + 1;
                        int partitionY = k == 2 ? y + 1 : y;
                        if(place[partitionX].charAt(partitionY) != 'X')
                        {
                            return 0;
                        }
                        break;
                    }
                case 4, 5: //아래 왼쪽, 아래 오른쪽 대각선
                    {
                        if(place[nx].charAt(ny) != 'P')
                            continue;
                        
                        //(x,y) 좌표기준으로 바로 밑에 있는 공간은 무조건 확인
                        int partitionX = x + 1;
                        int partitionY = y;
                        if(place[partitionX].charAt(partitionY) != 'X')
                        {
                            return 0;
                        }
                        //(x,y) 좌표기준으로 좌우에 있는 공간 확인
                        partitionX = x;
                        partitionY = k == 4 ? y - 1 : y + 1;
                        if(place[partitionX].charAt(partitionY) != 'X')
                        {
                            return 0;
                        }
                        break;
                    }
            }
        }
        return 1;
    }
    public List<Integer> solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        for(String[] place : places)
        {
            answer.add(checkPlace(place));
        }
        
        return answer;
        
    }
}
