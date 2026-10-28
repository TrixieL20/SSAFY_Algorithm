class Solution {
    public String solution(String number, int k) {
    	StringBuilder sb = new StringBuilder();
    	
    	int length = number.length();
    	
    	// i 번째 숫자 => i-1 번째 숫자의 인덱스 < i번째 숫자의 인덱스 < k + i + 1 고려해 가장 큰 수 뽑기
    	// 이전 숫자의 인덱스 저장
    	int preIdx = -1;
    	// i 번째 숫자 뽑기 
    	for (int i = 0; i < length-k; i++) {
        	int maxNum = '0' - 1;
        	int maxIdx = -1;
			for (int j = preIdx + 1; j <= k+i; j++) {
				if(number.charAt(j) > maxNum) {
					maxIdx = j;
					maxNum = number.charAt(j);
				}
			}
			preIdx = maxIdx;
			sb.append((char)maxNum);
		}
        return sb.toString();
    }
}