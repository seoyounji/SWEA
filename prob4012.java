package Algorithm_0828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//삼성 4012 요리사
public class prob4012 {

	private static int diff = Integer.MAX_VALUE;
	
	private static void cal(boolean[] visited,int[][] map,int n,int r) {
		int A=0; int B=0;
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if(visited[i]) {
				a.add(i);
			} else b.add(i);
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < r; j++) {
				A += map[a.get(i)][a.get(j)];
				B += map[b.get(i)][b.get(j)];
			}
		}
		int dif = Math.abs(A-B);
		diff = Math.min(diff, dif);
		return;
	}
	private static void DFS(int start,int n,int r,boolean[] visited,int[][] map) {
		if(start==n) return;
		if(r==0) {
			cal(visited,map,n,n/2);
			return;
		}
		for (int i = start; i <= n; i++) {
			visited[i] = true;
			DFS(i+1,n,r-1,visited,map);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= TestCase; i++) {
			diff = Integer.MAX_VALUE;
			int N = Integer.parseInt(bf.readLine());
			int[][] map = new int[N+1][N+1];
			for (int j = 1; j <= N; j++) {
				String[] tmp = bf.readLine().split(" ");
				for (int k = 1; k <= N; k++) {
					map[j][k] = Integer.parseInt(tmp[k-1]);
				}
			}
			boolean[] visited = new boolean[N+1];
			DFS(1,N,N/2,visited,map);
			System.out.println("#"+i+" "+diff);
		}
	}
}
