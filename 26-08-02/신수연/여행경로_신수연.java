import java.util.*;

class Solution {
   
    List<String> result = new LinkedList<>();
    
    public void dfs(int depth, String start, String path, String[][] tickets, boolean[] visited) {
        if(depth == tickets.length) {
            result.add(path);
            return;
        }
        
        for(int i = 0; i < visited.length; i++) {
            if(tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets, visited);
                
                visited[i] = false;
            }
        }
    }
    public String[] solution(String[][] tickets) {
        boolean visited[] = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets, visited);
        
        Collections.sort(result);
       
        return result.get(0).split(" ");
    }
}