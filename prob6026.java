package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 6026 성수의 비밀번호 공격
public class prob6026 {

	private static int MOD = 1000000007;
	
	private static long POW(long x,int y) {
		long result = 1;
		while(y>0) {
			if(y%2==1) result = (result*x)%MOD;
			y = y/2;
			x = (x*x)%MOD;
		}
		return result;
	}
	private static long combi(int m,int k) {
		long up = 1;
		long bottom = 1;
		for (int i = m; i > m-k; i--) {
			up = (up*i)%MOD;
		}
		for (int i = 1; i <= k; i++) {
			bottom = (bottom*i)%MOD;
		}
		long result = POW(bottom,MOD-2);
		long res = (up*result)%MOD;
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			long result = 0;
			int ind = 1;
			for (int j = m; j >= 1; j--) {
				result += (ind*combi(m,j)*Math.pow(j, n))%MOD;
				ind = ind*(-1);
			}
			
			System.out.println("#"+i+" "+(result%MOD));
		}
		
		
	}

}
