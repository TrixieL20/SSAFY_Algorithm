# 문제 정보
| SWEA | [S/W 문제해결 기본] 2일차 - Ladder1 | D4  | [바로가기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh) |
<br>

# 접근 방법 / 핵심 아이디어
bfs로 접근하였다, 도착점으로 부터 출발하여 출발점을 찾게 했다.
4방을 탐색하는게 아닌 위로 올라가면서 옆에 길이 있다면 이동후 위로 올라가게 하여서 y좌표가 0에 도달하면 탐색을 끝내도록함


<br>

# 시간복잡도
O(n^2) 
n = 100 이므로 10000번 시행 미만

<br>

# 어려웠던 점 / 배운점

경로가 어차피 정해져 있으니까, bfs말고 역으로 시뮬레이션 한다면 빨리 풀 수 있을 것 같다.


<br>

# 코드 리뷰 반영

* [] 리뷰 반영 완료
* ## 반영 내용
 