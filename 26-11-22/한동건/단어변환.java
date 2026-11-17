import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        
        // words의 인덱스와, depth 넣기
        Deque<int[]> que = new ArrayDeque<>();
        
        // begin부터 큐에 넣어서... words 맨뒤에 begin을 넣는다(큐를 이미 Integer을 넣는거로 상정해서 후수정했음)
        int n = words.length;
        words = Arrays.copyOf(words, n + 1);  // 마지막 칸은 null
        words[n] = begin;

        boolean[] used = new boolean[n + 1];
        used[n] = true;
        que.offer(new int[]{n, 0});
        
    
        
        boolean flag = false;
        while(!que.isEmpty()){
            int[] curWordInfos = que.poll();
            
            String curWord = words[curWordInfos[0]];
            int curDepth = curWordInfos[1];
            
            if(curWord.equals(target))
            {
                flag = true;
                answer = curDepth;
                break;
            }
            // 현 단어와 유사한 단어 큐에 넣기 로직
            for(int i=0; i<words.length;i++){
                
                // 한 단어 차이잉고 사용하지 않았을때
                if(checkSmilar(curWord, words[i]) && !used[i]){
                    que.offer(new int[]{i,curDepth+1});
                    used[i] = true;
                }
                    
            }
            
        }
        
        if(!flag){
            return 0;
        }
        
        return answer;
    }
    
    private static boolean checkSmilar(String curWord, String otherWord){
        
        int diff = 0;
        
        for(int i=0; i<curWord.length(); i++){
            if(curWord.charAt(i)!=otherWord.charAt(i)){
                diff++;
            }
            // 틀린 글자 수가 1이상이면 false 반환
            if(diff >1)
                return false;
        }
        // 틀린 글자 수가 0개면 false반환
        if(diff==0)
            return false;
        
        // 틀린 글자 수가 1개 일때문 true반환
        return true;
        
    }
    
    
    
}