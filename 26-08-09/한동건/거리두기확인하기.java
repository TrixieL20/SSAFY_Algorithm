class Solution {
    // 동서남북
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { 1, -1, 0, 0 };
    // 좌상, 우상, 좌하, 우하
    static final int[] dx1 = { -1, -1, 1, 1 };
    static final int[] dy1 = { -1, 1, -1, 1 };
    static final int ROOM_SIZE = 5;
    static final int CASE_N = 5;

    public boolean is_in_range(int x, int y) {
        return (x >= 0 && y >= 0 && x < ROOM_SIZE && y < ROOM_SIZE);
       
    }

    public boolean is_ok(int x, int y, char[][] places) {
        // 맨해튼 거리 1일때
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (is_in_range(nx, ny)) {
                if (places[nx][ny] == ('P')) {
                    return false;
                }
            }
        }
        // 맨해튼 거리(직선) 2일때
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * 2;
            int ny = y + dy[i] * 2;

            if (is_in_range(nx, ny)) {
                // 맨해튼 거리(직선)가 2인곳이 'P'이고 중간에 파티션이 없을때 false 반환
                if (places[nx][ny] == ('P') && !(places[x + dx[i]][y + dy[i]] == ('X'))) {
                    return false;
                }

            }
        }

        // 대각 거리두기 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx1[i];
            int ny = y + dy1[i];

            if (is_in_range(nx, ny)) {
                // 대각선의 위치들이 'P'이고 파티션이 둘다 없을때 false 반환
                if (places[nx][ny] == ('P') && !(places[x + dx1[i]][y] == ('X') && places[x][y + dy1[i]] == ('X'))) {
                    return false;
                }
            }
        }

        return true;
    }

    public int checkRoom(char[][] room) {
        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                if (room[i][j] == 'P') {
                    if (!(is_ok(i, j, room))) {
                        return 0;

                    }
                }
            }
        }
        return 1;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[CASE_N];

        char[][][] rooms = new char[CASE_N][ROOM_SIZE][ROOM_SIZE];

        for (int i = 0; i < ROOM_SIZE; i++) {
            for (int j = 0; j < ROOM_SIZE; j++) {
                // String을 Char배열로 변환
                char[] charArray = places[i][j].toCharArray();
                rooms[i][j] = charArray.clone();
            }
        }

        for (int k = 0; k < CASE_N; k++) {
            // for (int i = 0; i < ROOM_SIZE; i++) {
            //     for (int j = 0; j < ROOM_SIZE; j++) {
            //         if (rooms[k][i][j] == 'P') {
            //             if (!(is_ok(i, j, rooms[k]))) {
            //                 answer[k] = 0;
            //                 break;
            //             }
            //         }
            //     }

            // }
            answer[k] = checkRoom(rooms[k]);

        }

        return answer;
    }
}