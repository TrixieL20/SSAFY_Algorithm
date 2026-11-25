import java.util.*;

class Solution {
    
    private int min;
    
    public void dfs(String curr, int count, boolean[] visited, String target, String[] words) {

        // target에 도착하면 min값 업데이트 후 리턴
        if(target.equals(curr)) {
            min = Math.min(min, count);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(!visited[i] && isProper(curr, words[i])) {
                visited[i] = true;
                
                dfs(words[i], count + 1, visited, target, words);
                
                visited[i] = false;
            }
        }
    }

    // 1개의 알파벳만 다른지 체크
    public boolean isProper(String a, String b) {
        int count = 0;
        
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                count++;
            }
            if(count > 1) return false;
        }
        return true;
    }
    
    public int solution(String begin, String target, String[] words) {
        min = Integer.MAX_VALUE;
        
        dfs(begin, 0, new boolean[words.length], target, words);

        // target으로 이동할 수 없을 때 0 리턴
        if(min == Integer.MAX_VALUE) return 0;
        
        return min;
    }
}
