package ComputingThinking;

import java.util.Scanner;

//삼성 3238 이항계수 구하기
public class prob3238 {

    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        int test_case=sc.nextInt();
         
        for(int i=1; i<=test_case; i++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            int m = sc.nextInt();
            long[][] binomial = new long[m+2][m+2];
            for (int j = 0; j < m; j++) {
				binomial[j][0] = 1;
				for (int j2 = 1; j2 <= j; j2++) {
					binomial[j][j2] = (binomial[j-1][j2-1] + binomial[j-1][j2]) % m;
				}
			}
			int ret = 1;
			while (n!=0 || k!=0) {
				ret *= binomial[(int) (n%m)][(int) (k%m)];
				n /= m;
				k /= m;
				ret %= m;
			}
			System.out.println("#"+i+" "+ret);
        }
    }
}
