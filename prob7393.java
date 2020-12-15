package Algorithm_0922;

import java.util.Scanner;

//삼성 7393 대규의 팬덤활동, 백준 1562 계단 수
public class prob7393 {

	private static int bit=1<<10;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int i = 1; i <= test_case; i++) {
			int N = sc.nextInt();
			
			long result = 0;
			long array[][][] = new long[101][10][bit];
			for (int j = 1; j < 10; j++) {
				array[1][j][1<<j] = 1;
			}
			for (int j = 2; j <=N; j++) {
				for (int j2 = 0; j2 <= 9; j2++) {
					for (int k = 0; k < bit; k++) {
						int tmp = k | (1<<j2);
						long x=0;
						if(j2>0) x=array[j-1][j2-1][k];
						long y=0;
						if(j2<9) y=array[j-1][j2+1][k];
						array[j][j2][tmp] = ((array[j][j2][tmp] + ((x+y)%1000000000))) %1000000000;
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				result = (result+array[N][j][bit-1])%1000000000;
			}
			System.out.println("#"+i+" "+result);
		}		
	}
}
