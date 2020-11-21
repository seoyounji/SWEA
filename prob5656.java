package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 5656 벽돌 깨기
public class prob5656 {

	private static int N,W,H,min;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	private static class point {
		int r,c,cnt;
		point(int r,int c,int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	//i번째 구슬을 떨어뜨리기. count는 지금까지 떨어뜨린 구슬의 개수. map은 이전 떨어뜨린 구슬까지의 2차원 배열
	private static void go(int count,int[][] map) {
		
		if(count == N) { //구슬을 다 떨어뜨리면 남아있는 벽돌의 개수를 구해서 최소값 갱신
			int result = getRemain(map);
			min = Math.min(result, min);
			return;
		}
		
		int[][] newMap = new int[H][W];
		
		//모든 열에 떨어뜨리는 시도
		for (int c = 0; c < W; c++) {
			int r = 0;
			while(r<H && map[r][c]==0) ++r;
			if(r==H) { //벽돌이 없을때 구슬을 떨어뜨리면 맵 변화가 없음
				go(count+1,map);
			} else {
				copy(map,newMap); //배열 복사
				boom(newMap,r,c); //터뜨리기
				down(newMap); //벽돌 내리기
				go(count+1,newMap); //다음 구슬 처리
			}
		}
	}
	
	private static void down(int[][] map) {
		for (int i = 0; i < W; i++) { //열은 고정
			int r = H-1;
			while(r>0) {
				if(map[r][i]==0) {
					int nr = r-1;
					while(nr>0 && map[nr][i]==0) --nr; //처음 만나는 벽돌 찾기
					map[r][i] = map[nr][i];
					map[nr][i] = 0;
				}
				--r;
			}
		}
	}

	private static void boom(int[][] map, int r, int c) {
		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(r,c,map[r][c]));
		map[r][c] = 0; //벽돌을 제거(방문처리 효과를 낸다)
		while(!queue.isEmpty()) {
			point tmp = queue.poll();
			if(tmp.cnt==1) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = tmp.r;
				int nc = tmp.c;
				for (int j = 1; j < tmp.cnt; j++) {
					nr += dx[i];
					nc += dy[i];
					if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]!=0) {
						queue.offer(new point(nr,nc,map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

	private static int getRemain(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]>0) count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			for (int j = 0; j < H; j++) {
				st = new StringTokenizer(bf.readLine());
				for (int j2 = 0; j2 < W; j2++) {
					map[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			go(0,map);
			System.out.println("#"+t+" "+min);
			
		}
	}

}
