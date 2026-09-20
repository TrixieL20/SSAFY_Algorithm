import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Solution {
	// 갱신할 최댓값
	static int maxAnswer;
	static List<Set<String>> visited;
	
	public static void cardSwap(List<Integer> cards, int index, int exchange) {
		// 교환횟수 다 씀
		if(index == exchange) {
			int ans = 0;
			for(int card: cards) {
				ans = 10 * ans + card;
			}
			maxAnswer = Math.max(ans, maxAnswer);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
        // 비교 편하게 문자열로 만듦
		for(int c: cards) {
			sb.append(c);
		}
		
        // 중복 탐색 방지
		if(visited.get(index).contains(sb.toString())) {
			return;
		}
		
        // 중복 아닌 경우 visited에 추가
		visited.get(index).add(sb.toString());

        // 교환 + 백트레킹
		for(int i = 0; i < cards.size()-1; i++) {
			for(int j = i+1; j < cards.size(); j++) {
				Collections.swap(cards, i, j);
				cardSwap(cards, index+1, exchange);
				Collections.swap(cards, i, j);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sc = new StringBuilder();
		
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			sc.append("#").append(test_case).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] s = st.nextToken().split("");
			int exchange = Integer.parseInt(st.nextToken());
			int[] cards = new int[s.length];
			
			for (int i = 0; i < s.length; i++) {
				cards[i] = Integer.parseInt(s[i]);
			}
			
			maxAnswer = 0;
			List<Integer> cardsList = new ArrayList<>(Arrays.stream(cards).boxed().collect(Collectors.toList()));
			visited = new ArrayList<>();
			
			for(int i = 0; i < exchange; i++) {
                visited.add(new HashSet<>());
            }

			cardSwap(cardsList, 0, exchange);
			sc.append(maxAnswer).append("\n");
		}
		
		System.out.println(sc.toString());
		
	}
}

