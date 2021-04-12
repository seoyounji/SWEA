package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 7258 혁진이의 프로그램 검증
public class prob7258 {

	private static int R,C;
	private static boolean[][][][] visited;
	
	private static class point {
		int x; int y; int mem; int dir;
		point(int x,int y,int mem,int dir) {
			this.x = x;
			this.y = y;
			this.mem = mem;
			this.dir = dir;
		}
	}
	
	private static boolean BFS(point start,char[][] map) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		Queue<point> queue = new LinkedList<>();
		queue.offer(start);
		//visited[start.x][start.y][start.mem][start.dir] = true;
		while(!queue.isEmpty()) {
			point tmp = queue.poll();
			if(visited[tmp.x][tmp.y][tmp.mem][tmp.dir]) continue;
			visited[tmp.x][tmp.y][tmp.mem][tmp.dir] = true;
			switch(map[tmp.x][tmp.y]) {
			case('<') :
				tmp.dir = 2;
				break;
			case('>') :
				tmp.dir = 3;
				break;
			case('^') :
				tmp.dir = 0;
				break;
			case('v') :
				tmp.dir = 1;
				break;
			case('_') :
				if(tmp.mem == 0) tmp.dir = 3;
				else tmp.dir = 2;
				break;
			case('|') :
				if(tmp.mem == 0) tmp.dir = 1;
				else tmp.dir = 0;
				break;
			case('?') :
				for (int i = 0; i < 4; i++) {
					int x = tmp.x + dx[i];
					int y = tmp.y + dy[i];
					if(x < 0) x = R-1;
					if(x >= R) x = 0;
					if(y < 0) y = C-1;
					if(y >= C) y = 0;
					queue.offer(new point(x,y,tmp.mem,i));
				}
				break;
			case('.') :
				break;
			case('@') :
				return true;
			case('+') :
				if(tmp.mem == 15) tmp.mem = 0;
				else tmp.mem += 1;
				break;
			case('-') :
				if(tmp.mem == 0) tmp.mem = 15;
				else tmp.mem -= 1;
				break;
			default:
				tmp.mem = map[tmp.x][tmp.y] - '0'; 
				break;
			}
			int x = tmp.x + dx[tmp.dir];
			int y = tmp.y + dy[tmp.dir];
			if(x < 0) x = R-1;
			if(x >= R) x = 0;
			if(y < 0) y = C-1;
			if(y >= C) y = 0;
			queue.offer(new point(x,y,tmp.mem,tmp.dir));
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			char[][] map = new char[R][];
			for (int i = 0; i < R; i++) {
				map[i] = bf.readLine().toCharArray();
			}
			visited = new boolean[R][C][16][4];

			String result = BFS(new point(0,0,0,3),map) ? "YES" : "NO";
			System.out.println("#"+t+" "+result);
		}
		
	}

}
