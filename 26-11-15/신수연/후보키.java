import java.util.*;

class Solution {
    private List<Set<Integer>> keys = new ArrayList<>();
    
    public void dfs(int start, int depth, int max, Set<Integer> visited, String[][] relation) {
        
        if(depth == max) {
            for(Set<Integer> key: keys) {
                if(visited.containsAll(key)) return;
            }
            Set<String> set = new HashSet<>();
        
            for(int i = 0; i < relation.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int v: visited) {
                    sb.append(relation[i][v]).append(",");
                }
                if(!set.add(sb.toString())) {
                    return;
                }
            }
            keys.add(new HashSet<>(visited));
            return;
        }
        
        
        for(int i = start; i < relation[0].length; i++) {
            visited.add(i);
            dfs(i + 1, depth + 1, max, visited, relation);
            visited.remove(i);
        }
    }
    public int solution(String[][] relation) {
        for(int i = 1; i <= relation[0].length; i++) {
            dfs(0, 0, i, new HashSet<>(), relation);
        }
        return keys.size();
        
    }
}
