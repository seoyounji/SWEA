package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 1949 등산로 조성
public class prob1949 {

	private static int N,K,result;
	
	private static class point {
		int x; int y; int before; boolean use; boolean[][] visited;
		point(int x, int y, int before, boolean use, boolean[][] visited) {
			this.x = x;
			this.y = y;
			this.before = before;
			this.use = use;
			this.visited = visited;
		}
	}
	
	private static boolean[][] copy(boolean[][] original) {
		boolean[][] rest = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rest[i][j] = original[i][j];
			}
		}
		return rest;
	}
	
	private static void BFS(int[][] map,point start) {
		int dist = 0;
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		Queue<point> queue = new LinkedList<>();
		start.visited[start.x][start.y] = true;
		queue.offer(start);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				point tmp = queue.poll();
				boolean[][] ttt = tmp.visited;
				for (int j = 0; j < 4; j++) {
					int x = tmp.x + dx[j];
					int y = tmp.y + dy[j];
					if(x<0 || y<0 || x>=N || y>=N) continue;
					if(ttt[x][y]) continue;
					if(map[x][y] >= tmp.before && tmp.use) continue;
					if(map[x][y] >= tmp.before && !tmp.use) {
						if(map[x][y] - K < tmp.before) {
							boolean[][] tttt = copy(ttt);
							tttt[x][y] = true;
							queue.offer(new point(x,y,tmp.before-1,true,tttt));
							
						}
					} 
					else if(map[x][y] < tmp.before){
						boolean[][] tttt = copy(ttt);
						tttt[x][y] = true;
						queue.offer(new point(x,y,map[x][y],tmp.use,tttt));
						
					}
				}
			}
			dist++;
		}
		
		result = Math.max(result, dist);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int max = 0;
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == max) {
						boolean[][] visited = new boolean[N][N];
						BFS(map,new point(i,j,max,false,visited));
					}
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}

}
