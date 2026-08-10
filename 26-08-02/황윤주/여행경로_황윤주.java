
public class 여행경로_황윤주 {

	import java.util.*;

	class Solution {
	    
	    static ArrayList<String> answer = new ArrayList<>();
	    static boolean[] visited;
	    static String[][] tickets;
	    
	    public String[] solution(String[][] tickets) {
	        this.tickets = tickets;
	        visited = new boolean[tickets.length];
	        
	        Arrays.sort(tickets, (a,b) -> {
	            if(a[0].equals(b[0])){
	                return a[1].compareTo(b[1]);
	            }
	            return a[0].compareTo(b[0]);
	        });
	        ArrayList<String> path = new ArrayList<>();
	        path.add("ICN");
	        dfs("ICN", path);
	        return answer.toArray(new String[0]);
	   
	    }
	    
	    static boolean dfs(String now, ArrayList<String> path){
	        if(path.size()==tickets.length+1){
	            answer = new ArrayList<>(path);
	            return true;
	        }
	        
	        for(int i=0; i<tickets.length; i++){
	            if(!visited[i]&&tickets[i][0].equals(now)){
	                visited[i] = true;
	                path.add(tickets[i][1]);
	                
	                if(dfs(tickets[i][1], path)) return true;
	                path.remove(path.size()-1);
	                visited[i]=false;
	            }
	        }
	        
	        return false;
	    }
	}
}
