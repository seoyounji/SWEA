package ComputingThinking;

import java.util.Scanner;

//삼성 3376 파도반  수열
public class prob3376 {

	public static void main(String[] args) {
		long[] array = new long[100];
		array[0] = 1;
		array[1] = 1;
		array[2] = 1;
		array[3] = 2;
		array[4] = 2;
		for (int i = 5; i < 100; i++) {
			array[i] = array[i-5] + array[i-1];
		}
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int i = 1; i <= test_case; i++) {
			System.out.println("#"+i+" "+array[sc.nextInt()-1]);
		}

	}

}
