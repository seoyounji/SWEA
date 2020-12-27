package Algorithm_0828;

import java.util.Scanner;

//삼성 3234 준환이의 양팔저울
public class prob3234 {

	private static int N,result;
	
	private static void DFS(int[] arr,boolean[] visited,int left,int right,int start,int total) {
		if(start==N) {
			result++;
			return;
		}
		if(left >= right+total) {
			int tmp = 1;
			for (int i = 1; i <= N-start; i++) {
				tmp*=2;
			}
			for (int i = 1; i <= N-start; i++) {
				tmp*=i;
			}
			result+=tmp;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			if(right + arr[i] <= left) {
				DFS(arr,visited,left,right + arr[i],start+1,total-arr[i]);
			}
			DFS(arr,visited,left+arr[i],right,start+1,total-arr[i]);
			
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();
		for (int i = 1; i <= TestCase; i++) {
			result = 0;
			int total = 0;
			N = sc.nextInt();
			int[] array = new int[N];
			boolean[] visited = new boolean[N];
			for (int j = 0; j < N; j++) {
				array[j] = sc.nextInt();
				total += array[j];
			}
			DFS(array,visited,0,0,0,total);
			System.out.println("#"+i+" "+result);
		}
	}
}
