package Algorithm_0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//삼성 1767 프로세서 연결하기
public class prob1767 {

	private static int[][] map;
	private static int N,max,min,totalCnt; //판크기,최대코어수,최소전선길이,처리할코어수
	private static ArrayList<int[]> list;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,-1,0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int TestCase = Integer.parseInt(st.nextToken());
		for (int i = 0; i < TestCase; i++) {
			N = Integer.parseInt(bf.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>(); //처리해야할, 가장자리가 아닌 코어들을 저장할 리스트
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					map[j][j2] = Integer.parseInt(st.nextToken());
					if(map[j][j2]==1) { //가장자리에 있지 않은 코어 리스트에 추가하기
						if(j==0 || j2==0 || j==N-1 || j2==N-1) continue; //가장자리에 있는 코어 
						list.add(new int[]{j,j2}); //가장자리에 있지 않은 코어
						totalCnt++;
					}
				}
			}
			go(0,0);
			System.out.println("#"+(i+1)+" "+min);
		}
	}
	
	private static void go(int index,int cCnt) { //index:처리할 코어의 index , cCnt:직전까지 살펴본 코어수
		if(index==totalCnt) {
			int res = getLength(); //놓아진 전선의 길이
			if(max<cCnt) {
				max=cCnt;
				min=res;
			} else if(max==cCnt) {
				min = Integer.min(min, res);
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0]; int c = cur[1];
		//해당 코어 선택 후 4방향의 직선으로 전선 놓아보는 시도
		for (int i = 0; i < 4; i++) {
			if(isAvailable(r,c,i)) { 
				setStatus(r,c,i,2); //해당 방향으로 전선 놓는게 가능한지 체크하고 가능하다면 전선 놓기 : 맥시노스판에 2로 입력
				go(index+1,cCnt+1); //다음 코어로 넘어가기
				setStatus(r,c,i,0); //놓았던 전선 지우기 (맥시노스판 원래대로 위치시키기) : 맥시노스판에 0으로 입력
			}
			
		}
		//해당 코어 비선택. 아무런 전선도 놓지 않고 다음 코어로 넘어가기
		go(index+1,cCnt);
	}

	
	//현코어의 위치에서 해당 방향으로 전선을 놓는게 가능한지 체크
	private static boolean isAvailable(int r,int c,int d) {
		int nr=r,nc=c;
		while(true) {
			nr+=dx[d];
			nc+=dy[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; //코어의 전선이 가장자리의 전원에 연결된 경우
			if(map[nr][nc]>=1) return false; //맥시노스판에서 1이면 코어, 2면 전선이므로 중간에 장애물을 만난 경우
		}
		return true;
	}
		
	//현코어의 위치에서 해당 방향으로 전선을 놓거나(2) 지우는(0) 함수
	private static void setStatus(int r,int c,int d,int value) {
		int nr=r,nc=c;
		while(true) {
			nr += dx[d];
			nc += dy[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; //경계를 벗어나면 map 값 수정 끝이므로 break
			map[nr][nc] = value; //가장자리도 값을 바꿔야함
		}
	}
	
	//연결된 전선의 길이 구하는 함수
	private static int getLength() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==2) ++cnt;
			}
		}
		return cnt;
	}
}
