class Solution {

    // 간선 저장
    static class Edge{
        int to; // 정점
        int cost; // 비용

        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    static ArrayList<Edge>[] graph;

    // Prim : 그래프의 모든 정점을 최소 비용으로 연결하는 MST(최소 신장 트리)를 만드는 알고리즘
    static int prim(int start){

        // cost가 작은 Edge부터 출력
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->a.cost - b.cost);
        boolean[] visited = new boolean[graph.length];

        // 시작 정점 pq에 넣기
        pq.offer(new Edge(start, 0));

        int total = 0;
        int count = 0;

        while(!pq.isEmpty()){
            // pq에서 가장 비용 작은 간선 꺼내기
            Edge cur = pq.poll();

            // 이미 방문한 정점이면 버리기
            if(visited[cur.to]) continue;

            // 처음 방문한 정점이면 비용 누적
            visited[cur.to] = true;
            total += cur.cost;
            count++;

            if(count==V) break;

            // 그 정점에서 갈 수 있는 간선을 pq에 넣기
            for(Edge next : graph[cur.to]){
                if(!visited[next.to]) pq.offer(next);
            }
        }

        return total;
    }

    public int solution(int n, int[][] costs) {

        // 인접 리스트 선언
        graph = new ArrayList[n];

        // 각 정점의 리스트 생성
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }


        // costs를 인접 리스트에 저장 (간선 저장)
        for(int[] cost : costs){

            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];

            // 무방향 그래프 저장
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        int cost = prim(0);
        return cost;
    }
}