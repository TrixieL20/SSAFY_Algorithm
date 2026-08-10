import java.util.*;

class Solution {
    static List<String> answer = new ArrayList<>();
    public List<String> solution(String[][] tickets) {        
        Arrays.sort(tickets, (a, b)->a[1].compareTo(b[1]));
        
        answer.add("ICN");
        dfs(tickets, "ICN");
         
        return answer;
    }
    
    public static boolean dfs(String[][] tickets, String start)
    {
        System.out.println("start: " + start);
        System.out.print("answer: ");
        answer.forEach(s->System.out.printf("%s ", s));
        System.out.println();
        
        System.out.print("ticket: ");
        for(String[] ticket : tickets)
        {
            System.out.printf("[%s %s]", ticket[0], ticket[1]);
        }
        System.out.println();
        if(answer.size() == tickets.length + 1)
        {
            return true;
        }
        
        for(int i = 0; i < tickets.length; i++)
        {
            if(!start.equals(tickets[i][0]))
                continue;
            
            answer.add(tickets[i][1]);
            tickets[i][0] = "";
            if(dfs(tickets, tickets[i][1])){
                return true;
            }
            tickets[i][0] = start;
            answer.remove(answer.size() - 1);
        }
        
        return false;
    }
}
