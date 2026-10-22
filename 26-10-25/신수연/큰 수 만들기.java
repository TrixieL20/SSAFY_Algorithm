import java.util.*;

class Solution {
    public String solution(String number, int k) {

        Stack<Integer> stack = new Stack<>();
        stack.add(number.charAt(0) - '0');
        
        for(int i = 1; i < number.length(); i++) {
            int next = number.charAt(i) - '0';
            
            while(!stack.isEmpty()) {
                int curr = stack.peek();
                
                if(curr >= next || k <= 0) break;
                
                stack.pop();
                k--;
            }
        
            if(number.length() - k == i) {
                k--;
                continue;
            }
            stack.push(next);
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }
        return sb.toString();
    }
}
