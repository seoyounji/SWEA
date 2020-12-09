package ComputingThinking;

import java.util.Scanner;

//삼성 4530 극한의 청소 작업
public class prob4530 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int i = 1; i <= test_case; i++) {

			long A = sc.nextLong();
			long B = sc.nextLong();
			String aa = Long.toString(A);
			String bb = Long.toString(B);
			int Anum = aa.length();
			int Bnum = bb.length();
			if(A<0) Anum -= 1;
			if(B<0) Bnum -= 1;
			char[] a = aa.toCharArray();
			char[] b = bb.toCharArray();
			int[] AA = new int[Anum];
			int[] BB = new int[Bnum];
			int index = 0;
			for (int j = 0; j < a.length; j++) {
				if(a[j]=='-') continue;
				int tmp = a[j]-'0';
				if(tmp > 4) tmp -= 1;
				AA[index++] = tmp;
			}
			index = 0;
			for (int j = 0; j < b.length; j++) {
				if(b[j]=='-') continue;
				int tmp = b[j]-'0';
				if(tmp > 4) tmp -= 1;
				BB[index++] = tmp;
			}
			long resA=0, resB=0;
			index = 0;
			for (int j = Anum-1; j >= 0; j--) {
				resA += AA[j]*Math.pow(9, index++);
			}
			index = 0;
			for (int j = Bnum-1; j >= 0; j--) {
				resB += BB[j]*Math.pow(9, index++);
			}
			if(A>0 && B>0) System.out.println("#"+i+" "+Math.abs(resB-resA));
			else if(A<0 && B<0) System.out.println("#"+i+" "+Math.abs(resB-resA));
			else {
				if(A<0) resA *= -1;
				if(B<0) resB *= -1;
				System.out.println("#"+i+" "+(Math.abs(resB-resA)-1));
			}
		}
	}
}
