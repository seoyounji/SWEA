package ComputingThinking;

import java.util.Scanner;

//삼성 5515 2016년 요일 맞추기
public class prob5515 {

	public static void main(String[] args) {
	
		int[] array = new int[13];
		array[1] = 31;
		array[2] = 29;
		array[3] = 31;
		array[4] = 30;
		array[5] = 31;
		array[6] = 30;
		array[7] = 31;
		array[8] = 31;
		array[9] = 30;
		array[10] = 31;
		array[11] = 30;
		array[12] = 31;
		
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int i = 1; i <= test_case; i++) {
			int m = sc.nextInt();
			int d = sc.nextInt();
			int sum = 0;
			for (int j = 1; j < m; j++) {
				if(j==1) {
					sum += 30;
				} else {
					sum += array[j];
				}
			}
			if(m==1) {
				sum += (d-1);
			} else {
				sum += d;
			}
			sum = (sum+4) % 7;
			System.out.println("#"+i+" "+sum);
		}

	}

}
