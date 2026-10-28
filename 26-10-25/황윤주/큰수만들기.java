class Solution {
    public String solution(String number, int k) {
        // 앞에서부터 비교하면서 (앞자리수 < 뒷자리수) 면 앞자리수 값 빼기
        int count = 0;
        StringBuilder sb = new StringBuilder(number);

        int i=0;

        while(i<sb.length()-1 && count<k){

            int prev = sb.charAt(i)-'0';
            int next = sb.charAt(i+1)-'0';

            if(prev < next){
                sb.deleteCharAt(i);
                count++;

                // 삭제한 위치의 앞자리부터 다시 비교
                if(i>0) i--;

            }else{
                i++;
            }
        }

        // 아직 k개를 다 삭제하지 못했다면 뒤에서 삭제
        while(count<k){
            sb.deleteCharAt(sb.length()-1);
            count++;
        }



        return sb.toString();
    }
}