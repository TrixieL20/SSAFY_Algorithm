import java.util.*;

class Worng {
    static List<List<String>> paths = new ArrayList<>();
    static Map<String, Map<String, List<Integer>>> ticketMap = new HashMap<>();
    static int k;

    public String[] solution(String[][] tickets) {
        List<String> path = new ArrayList<String>();
        boolean[] visited = new boolean[tickets.length];

        k = tickets.length + 1;

        path.add("ICN");

        for (int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            ticketMap
                    .computeIfAbsent(from, key -> new TreeMap<>())
                    .computeIfAbsent(to, key -> new ArrayList<>())
                    .add(i);
        }

        dfs("ICN", path, visited);

        paths.sort((a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                int compare = a.get(i).compareTo(b.get(i));
                if (compare != 0) return compare;
            }
            return 0;
        });

        return paths.get(0).toArray(new String[0]);
    }

    static void dfs(String start, List<String> path, boolean[] visited) {
        if (path.size() == k) {
            paths.add(new ArrayList<>(path));
            return;
        }

        if(!ticketMap.containsKey(start)) {
            return;
        }

        for (String airport : ticketMap.get(start).keySet()) {
            List<Integer> idxList = ticketMap.get(start).get(airport);

            for (Integer idx : idxList) {
                if (visited[idx]) continue;

                visited[idx] = true;
                path.add(airport);

                dfs(airport, path, visited);

                path.remove(path.size() - 1);
                visited[idx] = false;
            }
        }
    }
}