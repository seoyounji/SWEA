package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 5215 햄버거 다이어트
public class prob5215 {

	private static int result;
	
	private static void DFS(int[][] array,int N,int L,int taste,int index) {
		
		if(L<0) return;
		if(L==0 || index==N) {
			result = Math.max(result, taste);
			return;
		}
		
		DFS(array,N,L-array[index][1],taste+array[index][0],index+1);
		DFS(array,N,L,taste,index+1);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] array = new int[N][2];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine());
				array[j][0] = Integer.parseInt(st.nextToken());
				array[j][1] = Integer.parseInt(st.nextToken());
			}
			result = 0;
			DFS(array,N,L,0,0);
			System.out.println("#"+i+" "+result);
		}

	}

}
