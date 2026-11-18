import java.util.*;

class Solution {
    
    static String[][] globalRelation;
    
    static int answer;
    
    static List<Set<Integer>> uniqueList; //  유일성 통과한 조합 저장
        
    public int solution(String[][] relation) {
        
        globalRelation = relation;
        
        answer = 0;
        
        uniqueList = new ArrayList<>();
        
        int rowLen = relation[0].length;
        
        boolean[] subset =  new boolean[rowLen];
        
        System.out.println(Arrays.deepToString(globalRelation));
        
        makeSubset(0,rowLen,subset);
        
         // 최소성 검사, 다른 유일 조합을 진부분집합으로 포함하면 탈락
        for(Set<Integer> a : uniqueList){
            boolean minimal = true;
            for(Set<Integer> b : uniqueList){
                if(a.size() > b.size() && a.containsAll(b)){
                    minimal = false;
                    break;
                }
            }
            if(minimal){
                answer++;
            }
        }
        
        return answer;
    }
    
    private void makeSubset(int idx, int rowLen, boolean[] subset){
       
        if(idx == rowLen){
            
            // 유일성 만족했으면, 최소성 검사를 위해 저장
            if(isUnique(subset)){
                // 현재 조합을 복사해서 저장
                Set<Integer> keySet = new HashSet<>();
                for(int i=0; i<rowLen; i++){
                    if(subset[i]){
                        keySet.add(i);
                    }
                }
                uniqueList.add(keySet);
            }
            return;
        }
        
        
        subset[idx] = true;
        makeSubset(idx+1,rowLen,subset);
        
        subset[idx] = false;
        makeSubset(idx+1,rowLen,subset);
        
    }
    
    private boolean isUnique(boolean[] subset){
        
        
        
        List<Integer> keyPos = new ArrayList<>();
        
        for(int i=0 ; i<subset.length;i++){
            if(subset[i]==true){
                keyPos.add(i);
            }
        }
        
        // 공집합일때
        if(keyPos.isEmpty()){
            return false;
        }
        
        for(int i=0; i<globalRelation.length;i++){
            // 비교할 기준 행 
            String standardRow ="";
            
            // 후보키가 될 속성 조합을 string으로 합친다
            for(int idx : keyPos){
                standardRow += ","+globalRelation[i][idx];
            }
            
            // 기준 행과 비교할 다른 행
            for(int j=i+1; j<globalRelation.length; j++){
                
                String anotherRow = "";
                for(int idx : keyPos){
                    anotherRow += ","+globalRelation[j][idx];
                }
                
                // 기준 행과 하나라도 같으면 후보키 적합하지않음 false 리턴
                if(standardRow.equals(anotherRow)){
                    return false;
                }
                
            }
        }
        
        return true;
    } 
    
}// end of class