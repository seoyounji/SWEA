package Algorithm_0728;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

//SWEA 1208 Flatten
class Flattern
{
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int dump = sc.nextInt();
            int[] tmp = new int[100];
            for (int i=0;i<100;i++) {
                tmp[i] = sc.nextInt();	
            }
            int t = 0;
            for (int i=0;i<dump;i++) {
                Arrays.sort(tmp);
				tmp[0]++;
                tmp[99]--;
                Arrays.sort(tmp);
            	t = tmp[99]-tmp[0];
    			
                if (t <= 1) {
                     break;
                }
 			}
            System.out.print("#");
            System.out.print(test_case);
            System.out.print("   ");
            System.out.println(t); 
		}
	}
}