package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 5607 [Professional] 조합
public class prob5607 {
	
	private static final int MOD = 1234567891;
	
	private static long pow(long bottom) {
		long result = 1;
		long x = bottom;
		int y = MOD-2;
		while(y>0) {
			if(y%2==1) result = (result*x) % MOD;
			y = y/2;
			x = (x*x) % MOD;
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long up = 1;
			long bottom = 1;
			for (int j = n; j > n-r; j--) {
				up = (up*j) % MOD;
			}
			for (int j = 1; j <= r; j++) {
				bottom = (bottom*j) % MOD;
			}
			long result = pow(bottom) % MOD;
			System.out.printf("#%d %d\n",i,(up*result)%MOD);
		}
		
		
	}
}