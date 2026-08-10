import java.util.*;

class Solution {

    static List<String> answer;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {

        // 알파벳 순서대로 탐색하기 위해 정렬
        Arrays.sort(tickets, (a, b) -> {

            if(a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }

            return a[0].compareTo(b[0]);
        });

        visited = new boolean[tickets.length];

        List<String> path = new ArrayList<>();
        path.add("ICN");

        dfs("ICN", path, tickets, 0);

        return answer.toArray(new String[0]);
    }

    static void dfs(String current, List<String> path, String[][] tickets, int count) {

        // 모든 티켓 사용 완료
        if(count == tickets.length) {
            answer = new ArrayList<>(path);
            return;
        }

        for(int i = 0; i < tickets.length; i++) {

            // 이미 사용한 티켓
            if(visited[i])
                continue;

            // 현재 공항에서 출발하는 티켓인지 확인
            if(!tickets[i][0].equals(current))
                continue;

            visited[i] = true;
            path.add(tickets[i][1]);

            dfs(tickets[i][1], path, tickets, count + 1);

            // 이미 정답 찾음
            if(answer != null)
                return;

            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}