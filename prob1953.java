package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 1953 탈주범 검거
public class prob1953 {

	private static int[][] mapp;
	private static boolean[][] visited;
	private static int N,M,R,C,L;
	private static go[][] map;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static class go {
		boolean up; boolean down; boolean left; boolean right;
		go(boolean up,boolean down,boolean left,boolean right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
	}
	private static void BFS() {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(R,C));
		visited[R][C] = true;
		int time = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				point tmp = queue.poll();
				for (int j = 0; j < 4; j++) {
					int x = tmp.x + dx[j];
					int y = tmp.y + dy[j];
					if(x<0 || y<0 || x>=N || y>=M) continue;
					if(visited[x][y]) continue;
					if(mapp[x][y]==0) continue;
					
					go temp = map[tmp.x][tmp.y];
					go temp2 = map[x][y];
					boolean flag = false;
					switch(j) {
					case(0):
						if(temp.up && temp2.down) flag = true;
						break;
					case(1):
						if(temp.down && temp2.up) flag = true;
						break;
					case(2):
						if(temp.left && temp2.right) flag = true;
						break;
					case(3):
						if(temp.right && temp2.left) flag = true;
						break;
					}
					
					if(flag) {
						queue.offer(new point(x,y));
						visited[x][y] = true;
					}
				}
			}
			time++;
			if(time==L) break;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			mapp = new int[N][M];
			visited = new boolean[N][M];
			map = new go[N][M];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine());
				for (int j2 = 0; j2 < M; j2++) {
					int tmp = Integer.parseInt(st.nextToken());
					mapp[j][j2] = tmp;
					go t = new go(false,false,false,false);
					switch(tmp) {
					case(1):
						t = new go(true,true,true,true);
						break;
					case(2):
						t = new go(true,true,false,false);
						break;
					case(3):
						t = new go(false,false,true,true);
						break;
					case(4):
						t = new go(true,false,false,true);
						break;
					case(5):
						t = new go(false,true,false,true);
						break;
					case(6):
						t = new go(false,true,true,false);
						break;
					case(7):
						t = new go(true,false,true,false);
						break;
					}
					map[j][j2] = t;
				}
			}
			int result = 0;
			if(L==1) result = 1;
			else {
				BFS();
				for (int j = 0; j < N; j++) {
					for (int j2 = 0; j2 < M; j2++) {
						if(visited[j][j2]) result++;
					}
				}				
			}
			System.out.println("#"+i+" "+result);			
		}

	}

}
