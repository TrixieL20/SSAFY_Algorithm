import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Solution {
	// 갱신할 최댓값
	static int maxAnswer;
	
	public static int findBestSwap(int[] cards, int exchange) {
		// 이번에 정렬해야 하는 자릿수(인덱스)
		int sortIndex = 0;
		List<Integer> cardsList = Arrays.stream(cards).boxed().collect(Collectors.toList());
		// 이번 정렬 차례에 와야 할 가장 큰 숫자 찾기 위해 미리 정렬
		// sortedCards.get(sortIndex)가 sortIndex 이후 인덱스들 중에서 가장 큰 숫자 !
		List<Integer> sortedCards = new ArrayList<>(cardsList);
		Collections.sort(sortedCards, Collections.reverseOrder());
		
		maxAnswer = 0;
		// dfs로 카드 교환 탐색
		exchangeCards(cardsList, sortedCards, sortIndex, exchange);
		return maxAnswer;
	}
	
	// 카드 교환 로직 들어간 dfs
	public static void exchangeCards(List<Integer> cardsList, List<Integer> sortedCards, int sortIndex, int exchange) {
		int cardNum = cardsList.size();
		
		// 교환횟수 다 쓴 경우 
		if(exchange == 0) {
			int ans = calculateNum(cardsList, cardNum);
			maxAnswer = Math.max(maxAnswer, ans);
			return;
		}
		
		// 교환횟수 남았으나 이미 최적 형태로 만든 경우
		if(sortIndex == cardNum) {
			// exchange가 짝수면 같은 카드 두 번 바꾸면 원점이므로 그대로 쓸 수 있음
			if(exchange % 2 == 0) {
				int ans = calculateNum(cardsList, cardNum);
				maxAnswer = Math.max(maxAnswer, ans);
			}
			// 교환 횟수 한 번은 남는 경우 
			else {
				boolean isSame = false;
				for(int i = 0; i < cardNum-1; i++) {
					if(sortedCards.get(i).equals(sortedCards.get(i+1))) {
						isSame = true;
						break;
					}
				}
				// 같은 숫자쌍 있는 경우 둘을 바꾸면 그대로이므로 똑같이 반환 가능
				if(isSame) {
					int ans = calculateNum(cardsList, cardNum);
					maxAnswer = Math.max(maxAnswer, ans);
				}
				// 아닌 경우 한 번은 바꿔야 함 -> 맨 뒤의 2개 swap 하고 백트레킹 ! 
				else {
					Collections.swap(cardsList, cardNum - 2, cardNum - 1);
	                maxAnswer = Math.max(maxAnswer, calculateNum(cardsList, cardNum));
	                Collections.swap(cardsList, cardNum - 2, cardNum - 1);
				}
			}
			return;
		}
		
		// 교환횟수 남았고 최적 형태도 아닌 경우
		
		// 이미 남은 것들 중 가장 큰 숫자가 sortedCards처럼 제자리 찾아 가 있는 경우
		// 교환할 필요 없음 -> 다음 진행
		if (cardsList.get(sortIndex).equals(sortedCards.get(sortIndex))) {
            exchangeCards(cardsList, sortedCards, sortIndex + 1, exchange);
            return;
        }
		
		// 교환해야 하는 경우
		for(int i = sortIndex + 1; i < cardNum; i++) {
			if(cardsList.get(i) == sortedCards.get(sortIndex)) {
				Collections.swap(cardsList, sortIndex, i);
				exchangeCards(cardsList, sortedCards, sortIndex+1, exchange-1);
				Collections.swap(cardsList, sortIndex, i);
			}
		}
	}
	
	public static int calculateNum(List<Integer> cardsList, int cardNum) {
		int ans = 0;
		for(int i = 0 ; i < cardNum; i++) {
			ans += cardsList.get(i) * Math.pow(10, cardNum - 1 - i);
		}
		return ans;
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
			
			sc.append(findBestSwap(cards, exchange)).append("\n");
		}
		
		System.out.println(sc.toString());
		
	}
}

