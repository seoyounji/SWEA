package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//삼성 1868 파핑파핑 지뢰찾기
public class prob1868 {

	private static int N,result;
	private static char[][] map;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int cal(point point) {
		int[] dx = {-1,-1,-1,0,0,1,1,1};
		int[] dy = {-1,0,1,-1,1,-1,0,1};
		int num = 0;
		for (int i = 0; i < 8; i++) {
			int x = point.x + dx[i];
			int y = point.y + dy[i];
			if(x<0 || y<0 || x>=N || y>=N) continue;
			if(map[x][y]=='*') num++;
		}
		return num;
	}
	
	private static void BFS(point tmp) {
		int[] dx = {-1,-1,-1,0,0,1,1,1};
		int[] dy = {-1,0,1,-1,1,-1,0,1};
		Queue<point> queue = new LinkedList<>();
		queue.offer(tmp);
		while(!queue.isEmpty()) {
			point point = queue.poll();
			for (int i = 0; i < 8; i++) {
				int x = point.x + dx[i];
				int y = point.y + dy[i];
				if(x<0 || y<0 || x>=N || y>=N) continue;
				if(map[x][y]!='.') continue;
				int num = cal(new point(x,y));
				map[x][y] = (char)num;
				if(num==0) {
					queue.offer(new point(x,y));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			N = Integer.parseInt(bf.readLine());
			map = new char[N][];
			for (int i = 0; i < N; i++) {
				map[i] = bf.readLine().toCharArray();
			}
			result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]=='.') {
						int num = cal(new point(i,j));
						if(num == 0) {
							map[i][j] = '0';
							BFS(new point(i,j));
							result++;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]=='.') result++;
				}
			}
			
			System.out.println("#"+t+" "+result);
		}
	}

}
