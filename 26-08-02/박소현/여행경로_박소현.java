import java.util.*;

class Solution {
    static ArrayList<String> answer = new ArrayList<String>();
    static void dfs(final int cityCnt, HashMap<String, ArrayList<String>> routes, String cur, ArrayList<String> temp)
    {
        if(temp.size() == cityCnt)
        {
            temp.forEach(item->answer.add(item));
            return;
        }
        
        ArrayList<String> dests = routes.get(cur);
        if(dests == null)
        {
            return;
        }
        for(String dest : dests)
        {
            if(temp.contains(dest))
            {
                continue;
            }
            
            temp.add(dest);
            dfs(cityCnt, routes, dest, temp);
            if(answer.size() == cityCnt)
                 return;
            temp.remove(dest);
        }
    }
    
    public ArrayList<String> solution(String[][] tickets) {
        HashMap<String, ArrayList<String>> routes = new HashMap<>();
        HashSet<String> cities = new HashSet<>();
        for(int i = 0; i < tickets.length; i++)
        {
            cities.add(tickets[i][0]);
            cities.add(tickets[i][1]);
            routes.merge(tickets[i][0], new ArrayList<>(List.of(tickets[i][1])), (oldList, newList)->{
                oldList.addAll(newList);
                return oldList;
            });
        }
        System.out.println("cities.size() " + cities.size());
        for(Map.Entry<String, ArrayList<String>> e : routes.entrySet())
        {
            ArrayList<String> arr = e.getValue();
            Collections.sort(arr);
        }
        
        for(Map.Entry<String, ArrayList<String>> e : routes.entrySet())
        {
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(e.getKey()));
            dfs(cities.size(), routes, e.getKey(), temp);
            if(answer.size() == cities.size())
            {
                return answer;
            }
        }
        
        return answer;
    }
}