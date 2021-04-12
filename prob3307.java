package Algorithm_0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 3307 최장 증가 부분 수열
public class prob3307 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			int N = Integer.parseInt(bf.readLine());
			int[] array = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				array[j] = Integer.parseInt(st.nextToken());
			}
			int result = 0;
			int[] tmp = new int[N];
			for (int j = 0; j < N; j++) {
				tmp[j] = 1;
			}
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < j; j2++) {
					if(array[j]>array[j2] && tmp[j]<1+tmp[j2]) {
						tmp[j] = 1+tmp[j2];
					}
				}
				result = Math.max(result, tmp[j]);
			}
			System.out.println("#"+i+" "+result);
		}
		
	}

}
