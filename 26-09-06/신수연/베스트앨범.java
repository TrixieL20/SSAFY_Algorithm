import java.util.*;

class Solution {
    
    public String searchMax(Map<String, Integer> total) {
        int max = Integer.MIN_VALUE;
        String key = "";

        for (String k : total.keySet()) {
            int value = total.get(k);

            if (value > max) {
                max = value;
                key = k;
            }
        }

        return key;
    }
    
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<int[]>> info = new HashMap<>();
        
        for(int i = 0; i < plays.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
            
            List<int[]> curr = info.getOrDefault(genres[i], new ArrayList<>());
            curr.add(new int[] {i, plays[i]});
            info.put(genres[i], curr);
        }
        
        for(String key: info.keySet()) {
            List<int[]> curr = info.get(key);
            curr.sort((o1, o2) -> {
                if(o2[1] == o1[1]) {
                    return o1[0] - o2[0];
                }
                return o2[1] - o1[1];
            });
            
            curr = info.get(key);
        }
    
        
        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < info.size(); i++) {
            String key = searchMax(total);
            total.remove(key);
            
            List<int[]> curr = info.get(key);
            int cnt = 0;
            for(int[] c : curr) {
                if(cnt == 2) break;
                
                answer.add(curr.get(cnt)[0]);
                cnt++;
            }
        } 
        

        return answer;
    }
}
