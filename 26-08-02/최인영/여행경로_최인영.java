import java.util.*;

public class 여행경로_최인영 {
    List<String> answer;
    boolean[] visited;
    String[][] tickets;
    
    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        visited = new boolean[tickets.length];
        this.tickets = tickets;
        
        Arrays.sort(tickets, (t1, t2) -> {
            int result = t1[0].compareTo(t2[0]);
            if(result == 0){
                return t1[1].compareTo(t2[1]);
            }
            return result;
        });
        
        answer.add("ICN");
        dfs("ICN", 0);
        
        return answer.toArray(new String[answer.size()]);
    }
    
    private boolean dfs(String cur, int usedTicket) {
    	if(usedTicket == tickets.length) {
    		return true;
    	}
    	
    	for(int i = 0; i < tickets.length; i++) {
    		if(visited[i]) { continue; }
    		if(! tickets[i][0].equals(cur)) { continue; }
    		
    		visited[i] = true;
    		answer.add(tickets[i][1]);
    		
    		if(dfs(tickets[i][1], usedTicket + 1)) {
    			return true;
    		}
    		
    		visited[i] = false;
    		answer.remove(answer.size()-1);
    	}
    	
    	return false;
    }
}
