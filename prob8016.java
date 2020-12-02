package ComputingThinking;

import java.util.Scanner;

//삼성 8016 홀수 피라미드
public class prob8016 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int i = 1; i <= test_case; i++) {
			long N = sc.nextLong();
			if(N==1) {
				System.out.println("#"+i+" "+1+" "+1);
			} else {
				long num = 1 + ((N-1)*(4+4*(N-2)))/2;
				long num2 = 1 + (N*(4+4*(N-1)))/2 - 2;
				System.out.println("#"+i+" "+num+" "+num2);
			}
		}
		
	}

}
