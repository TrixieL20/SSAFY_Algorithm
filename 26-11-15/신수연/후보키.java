import java.util.*;

class Solution {
    private List<Set<Integer>> keys = new ArrayList<>();
    
    public void dfs(int start, int depth, int max, Set<Integer> visited, String[][] relation) {
        
        if(depth == max) {
            // 이미 최소성을 만족한 후보키들이 선택됐으면 리턴
            for(Set<Integer> key: keys) {
                if(visited.containsAll(key)) return;
            }

            Set<String> set = new HashSet<>();
        
            for(int i = 0; i < relation.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int v: visited) {
                    sb.append(relation[i][v]).append(",");
                }
                // 이미 포함된 키라면 리턴
                if(!set.add(sb.toString())) {
                    return;
                }
            }

            // 위의 최소성 검사를 다 통과했을 때 새로운 후보키로 추가
            keys.add(new HashSet<>(visited));
            return;
        }
        
        // 키 선택
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
