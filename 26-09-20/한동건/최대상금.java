
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 접근:
 * 
 * 걍 조합 다 구하고 parseInt 해서 최대값 구하셈
 * 
 */

public class SWEA_1244_최대상금_D3 {

	static int N;
	static char[] str;
	static int maxVal;
	static Set<String> visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String[] line = br.readLine().split(" ");

			str = new char[line[0].length()];

			for (int i = 0; i < line[0].length(); i++) {
				str[i] = line[0].charAt(i);
			}

			N = Integer.parseInt(line[1]);
			maxVal = Integer.MIN_VALUE;
			
			// 메모제이션용 set
			// => 같은 깊이에서, 같은 str이 나온다면 그 뒤는 똑같기 때문에 가지치기 해준다.
			visited = new HashSet<>();
			
			getCombi(0);
			
			System.out.println("#"+tc+" "+maxVal);			
		}

	}// end of main

	public static void getCombi(int cnt) {

		if (cnt == N) {
			String toStr = new String(str);
			
			
			int val = Integer.parseInt(toStr);
			
			maxVal = Math.max(maxVal, val);
			
			return;
		}

		for (int i = 0; i < str.length - 1; i++) {
			for (int j = i + 1; j < str.length; j++) {
				swap(i, j);
				
				String curKey = cnt + new String(str);
				
				if(!visited.contains(curKey)) {
					visited.add(curKey);
					getCombi(cnt + 1);
				}
				
				swap(i, j);
			}
		}

	}

	public static void swap(int a, int b) {
		char tmp = 0;

		tmp = str[a];
		str[a] = str[b];
		str[b] = tmp;
	}
}// end of class
