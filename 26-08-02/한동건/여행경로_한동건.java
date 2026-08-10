import java.util.*;

class Solution {
    boolean[] visited;
    List<String> answer;
    int ticketCount;
    
    public String[] solution(String[][] tickets) {
        ticketCount = tickets.length;
        visited = new boolean[ticketCount];
        answer = new ArrayList<>();
        
        // 미리 정렬하면 dfs시 사전순으로 탐색가능
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        
        List<String> path = new ArrayList<>();
        path.add("ICN");
        
        dfs(tickets, "ICN", path);
        
        return answer.toArray(new String[0]);
    }
    
    private boolean dfs(String[][] tickets, String current, List<String> path) {
        // 티켓 다썻으면 끝
        if (path.size() == ticketCount + 1) {
            answer = new ArrayList<>(path);
            return true; // 처음 완성했으면 사전순 최소
        }
        
        for (int i = 0; i < ticketCount; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                path.add(tickets[i][1]);
                
                if (dfs(tickets, tickets[i][1], path)) {
                    return true; 
                }
                
                // 실패 시 백트래킹
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
        
        return false;
    }
}