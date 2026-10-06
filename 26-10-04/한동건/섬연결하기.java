import java.util.*;
class Solution {
    static int[] parents ;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 노드가 가르키는 집합의 대표노드 저장
        parents = new int[n];
        
        
        // 노드 세팅(자기자신)
        for(int i=0; i<n;i++){
            parents[i]=i;
        }
        
        // // 시작 섬을 작은 걸로 설정
        // for(int i=0; i<costs.length;i++){
        //     if(costs[i][0]>costs[i][1]){
        //         int tmp = costs[i][0];
        //         costs[i][0] = costs[i][1];
        //         costs[i][1] = tmp;
        //     }
        // }
        
        // 비용 낮은순 정렬
        Arrays.sort(costs,(a,b)->{
            if(a[2]==b[2]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[2],b[2]);
        });
        
        int cnt = 0;
       for(int i=0;i<costs.length;i++){
           int st = costs[i][0];
           int fi = costs[i][1];
           int c = costs[i][2];
           
           if(find(st)==find(fi))continue;
           
           union(st,fi);
           cnt++;
           answer+=c;
           
           // 간선 n-1개 선택하면 조기종료
           if(cnt==n-1)break;
           
           
       }
        
        return answer;
    }
     
    // 자기가 속한 집합의 대표노드 반환
    public static int find(int n){
        
        if(parents[n]==n){
            return n;
        }
        
        // 경로 압축
        return parents[n] = find(parents[n]);
        
    }
    
    public static int union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot==bRoot){
            return -1;
        }
        
        // 인덱스를 b말고 bRoot로 바꿔야함.. 이거 실수
        parents[bRoot] = aRoot;
        
        return aRoot;
    }
}