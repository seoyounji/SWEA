package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 2814 최장 경로
public class prob2814 {

	private static int result,N;
	private static boolean[][] map;
	//private static boolean[] visited;
	
	private static void DFS(int start,int dist,boolean[] visited) {
		for (int i = 1; i <= N; i++) {
			if(!visited[i] && map[start][i]) {
				visited[i] = true;
				DFS(i,dist+1,visited);
				visited[i] = false;
			}
		}
		result = Math.max(result, dist);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new boolean[N+1][N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = true;
				map[y][x] = true;
			}
			if(M==0) System.out.println("#"+t+" "+1);
			else if(M==1) System.out.println("#"+t+" "+2);
			else {
				for (int i = 1; i <= N; i++) {
					boolean[] visited = new boolean[N+1];
					visited[i] = true;
					DFS(i,1,visited);
					//visited[i] = false;
				}
				System.out.println("#"+t+" "+result);
			}
		}
	}

}
