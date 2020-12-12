package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 2805 농작물 수확하기
public class prob2805 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			int length = Integer.parseInt(bf.readLine());
			char[][] map = new char[length][];
			for (int j = 0; j < length; j++) {
				map[j] = bf.readLine().toCharArray();
			}
			int x = length/2;
			int result = 0;
			for (int j = 0; j < length; j++) {
				for (int j2 = 0; j2 < length; j2++) {
					if(Math.abs(x-j)+Math.abs(x-j2)<=x) {
						result += (map[j][j2]-'0');
					}
				}
			}
			System.out.println("#"+i+" "+result);
		}
	}
}
