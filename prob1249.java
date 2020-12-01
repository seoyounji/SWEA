package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//삼성 1249 보급로
public class prob1249 {

	private static int[][] result;
	private static int[][] map;
	private static int n;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS(point start) {
		Queue<point> queue = new LinkedList<>();
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		queue.offer(start);
		while(!queue.isEmpty()) {
			point tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = tmp.x + dx[i];
				int y = tmp.y + dy[i];
				if(x<0 || y<0 || x>=n || y>=n) continue;
				if(result[x][y] > result[tmp.x][tmp.y] + map[x][y]) {
					result[x][y] = result[tmp.x][tmp.y]+ map[x][y];
					queue.offer(new point(x,y));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		
		for (int i = 1; i <= test_case; i++) {
			n = Integer.parseInt(bf.readLine());
			map = new int[n][n];
			result = new int[n][n];
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					result[j][j2] = Integer.MAX_VALUE;
				}
			}
			result[0][0] = 0;
			for (int j = 0; j < n; j++) {
				String tmp = bf.readLine();
				for (int j2 = 0; j2 < n; j2++) {
					map[j][j2] = tmp.charAt(j2)-'0';
				}
			}
			BFS(new point(0,0));
			System.out.println("#"+i+" "+result[n-1][n-1]);
		}
	}

}
