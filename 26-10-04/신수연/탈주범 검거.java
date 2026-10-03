import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int type;
    int time;

    Node(int row, int col, int type, int time) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.time = time;
    }
}

class Solution {
    static int n;
    static int m;
    static int r;
    static int c;
    static int l;
    static int[][] map;
    static Map<Integer, Set<Integer>> dir;

    // 하상우좌
    static int[] mx = {1, -1, 0, 0};
    static int[] my = {0, 0, 1, -1};

    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c, map[r][c], 1));

        Set<String> visited = new HashSet<>();
        visited.add(r + " " + c);

        int count = 0;
        while (!q.isEmpty()) {
       
            Node curr = q.poll();

            count++;

            // time이 l에 도달하면 더 이상 q에 값을 추가하지 않음
            if (curr.time == l) continue;

            for (int i = 0; i < 4; i++) {
                int nx = curr.row + mx[i];
                int ny = curr.col + my[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited.contains(nx + " " + ny) || map[nx][ny] == 0) continue;

                // i번째 방향으로 갈 수 없으면 continue
                if (!dir.get(i).contains(curr.type)) continue;

                int opposite = i ^ 1;

                if (!dir.get(opposite).contains(map[nx][ny])) continue;

                q.offer(new Node(nx, ny, map[nx][ny], curr.time + 1));
                visited.add(nx + " " + ny);
            }
        }

        return count;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = br.readLine().split(" ");

            n = Integer.parseInt(input[0]); // 세로
            m = Integer.parseInt(input[1]); // 가로
            r = Integer.parseInt(input[2]); // 맨홀 세로
            c = Integer.parseInt(input[3]); // 맨홀 가로
            l = Integer.parseInt(input[4]); // 탈출 후 소요 시간

            map = new int[n][m];
            dir = new HashMap<>();

            //하상우좌
            dir.put(0, new HashSet<>(Arrays.asList(1, 2, 5, 6)));
            dir.put(1, new HashSet<>(Arrays.asList(1, 2, 4, 7)));
            dir.put(2, new HashSet<>(Arrays.asList(1, 3, 4, 5)));
            dir.put(3, new HashSet<>(Arrays.asList(1, 3, 6, 7)));

            for (int i = 0; i < n; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }
            int answer = bfs();
            sb.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(sb);
    }
}
