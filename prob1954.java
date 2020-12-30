package Algorithm_0728;

import java.util.Scanner;
import java.io.FileInputStream;

//SWEA 1954 달팽이 숫자
class snailNumber
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[] N = new int[T];
		for(int i=0;i<T;i++) {
			N[i] = sc.nextInt();
		}
		for(int test_case = 0; test_case < T; test_case++)
		{
			//int N = sc.nextInt();
			int tmp = N[test_case];
			int[][] map = new int[tmp][tmp];
			
			int value = 1;
			int row = 0, col = -1; 
			int inc = 1; 
			
			while (tmp > 0) { 
				for (int i = 0; i < tmp; i++) { 
					col += inc; 
					map[row][col] = value; 
					value++; 
				} 
				tmp--; 
				for (int i = 0; i < tmp; i++) { 
					row += inc; 
					map[row][col] = value; 
					value++; 
				} 
				inc *= -1;
			}
            System.out.print("#");
            System.out.println(test_case+1);
			for (int i=0;i<N[test_case];i++) {
				for (int j=0;j<N[test_case];j++) {
					System.out.print(map[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
	}
}