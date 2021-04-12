package Algorithm_0827;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 1247 최적 경로
public class prob1247 {

	private static int result = Integer.MAX_VALUE;
	private static point home,office;
	
	private static class point{
		int x;int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int distance(point a,point b) {
		return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
	}
	private static void perm(point[] arr,int[] output,boolean[] visited,int n,int start) {
		if(start==n) {
			int tmp = 0;
			point st = office;
			for (int i = 0; i < n; i++) {
				tmp += distance(arr[output[i]],st);
				st = arr[output[i]];
			}
			tmp += distance(st,home);
			result = Integer.min(result, tmp);
			return;
		}
		for (int i = 0; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			output[start] = i;
			perm(arr,output,visited,n,start+1);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= TestCase; i++) {

			int N = Integer.parseInt(bf.readLine());
			point[] customer = new point[N];
			StringTokenizer st = new StringTokenizer(bf.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			office = new point(x,y);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new point(x,y);
			for (int j = 0; j < N; j++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customer[j] = new point(x,y);
			}
			boolean[] visited = new boolean[N];
			int[] output = new int[N];
			
			result = Integer.MAX_VALUE;
			perm(customer,output,visited,N,0);
			
			System.out.println("#"+i+" "+result);
		}
			
		
	}
}
