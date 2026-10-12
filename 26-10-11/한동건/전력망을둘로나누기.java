import java.util.*;

class Solution {
    // 인접 리스트: lists[v] = v와 연결된 노드 목록 (1-indexed)
    static ArrayList<Integer>[] lists;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;   // 최솟값을 구할 거라 최댓값으로 초기화
        lists = new ArrayList[n + 1];     // 0번은 안 씀 (노드 번호가 1~n)

        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        // 무방향 그래프라서 양쪽에 다 넣어줌
        for (int i = 0; i < wires.length; i++) {
            int left = wires[i][0];
            int right = wires[i][1];
            lists[left].add(right);
            lists[right].add(left);
        }

        // ── 핵심: 간선을 하나씩 "끊어보고" → 양쪽 크기 측정 → "복구" ──
        for (int i = 0; i < wires.length; i++) {
            int left = wires[i][0];
            int right = wires[i][1];

            // [끊기] i번째 간선 제거 (양방향 모두)
            // Integer.valueOf 필수: remove(int)는 "인덱스"로 지우고,
            // remove(Object)는 "값"으로 지움. 여기선 값 right를 지워야 함
            lists[left].remove(Integer.valueOf(right));
            lists[right].remove(Integer.valueOf(left));

            // 끊긴 간선의 양 끝점은 반드시 서로 다른 컴포넌트에 속함
            // → 각각에서 BFS 하면 두 덩어리의 크기를 구할 수 있음
            int left_cnt = bfs(left, n);
            int right_cnt = bfs(right, n);

            answer = Math.min(answer, Math.abs(left_cnt - right_cnt));

            // [복구] 다음 간선을 시험하기 위해 원상복구 (백트래킹과 같은 패턴)
            lists[left].add(right);
            lists[right].add(left);
        }
        return answer;
    }

    // start에서 도달 가능한 노드 수를 셈 (★ start 자신은 세지 않음)
    public int bfs(int start, int n) {
        int answer = 0;
        boolean[] isVisited = new boolean[n + 1];  // 호출마다 새로 만들어서 초기화
        ArrayDeque<Integer> aq1 = new ArrayDeque<>();
        isVisited[start] = true;
        aq1.add(start);

        while (!aq1.isEmpty()) {
            int now = aq1.poll();
            for (int i = 0; i < lists[now].size(); i++) {
                int next = lists[now].get(i);
                if (isVisited[next]) continue;   // 이미 방문했으면 스킵
                answer++;                        // 새 노드를 발견할 때만 카운트
                isVisited[next] = true;          // 큐에 넣을 때 방문 처리 (중복 삽입 방지)
                aq1.add(next);
            }
        }
        return answer;   // 실제 컴포넌트 크기 - 1
    }
}