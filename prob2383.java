package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//SWEA 2383 점심 식사시간
public class prob2383 {

	private static int N, min, cnt; //맵 크기, 최소시간, 사람 수
	private static boolean[] selected; //부분집합 선택을 관리할 배열
	private static int[][] sList; //계단 정보
	private static final int M=1,W=2,D=3,C=4;
	private static ArrayList<Person> pList;
	
	private static class Person implements Comparable<Person>{
		int r,c,downCnt,status,time; //행,열,내려간 계단수, 상태, 입구도착시간
		
		public Person(int r,int c) {
			this.r = r;
			this.c = c;
		}

		public void init() {
			downCnt = 0;
			status = M;
			time = 0;
		}
		
		@Override
		public int compareTo(Person o) {
			return this.time - o.time; //오름차순
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			pList = new ArrayList<Person>();
			sList = new int[2][];
			min = Integer.MAX_VALUE;
			
			int c=0,k=0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					c = Integer.parseInt(st.nextToken());
					if(c==1) pList.add(new Person(i,j));
					else if(c>1) sList[k++] = new int[] {i,j,c};
				}
			}
			cnt = pList.size();
			selected = new boolean[cnt];
			
			divide(0);
			
			System.out.println("#"+t+" "+min);
		}

	}
	
	private static void divide(int index) { //부분집합으로 계단 배정하기
		if(index == cnt) {
			makeList();
			return;
		}
		selected[index] = true;
		divide(index+1);
		selected[index] = false;
		divide(index+1);
	}
	
	private static void makeList() { //계단의 배정된 상황에 따라 각각의 사람 리스트 생성
		ArrayList<Person> aList = new ArrayList<>();
		ArrayList<Person> bList = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			Person p = pList.get(i);
			p.init();
			if(selected[i]) {
				p.time = Math.abs(p.r - sList[0][0]) + Math.abs(p.c - sList[0][1]);
				aList.add(p);
			}
			else {
				p.time = Math.abs(p.r - sList[1][0]) + Math.abs(p.c - sList[1][1]);
				bList.add(p);
			}
		}
		int res = go(aList,bList);
		if(min > res) min = res;
	}
	
	//두 계단에 대해 각각의 사람 리스트를 내려가기 처리 후 소요시간 비교해서 더 큰 값을 리턴. 즉 모든 사람이 내려가는데 걸리는 시간을 결정
	private static int go(ArrayList<Person> aList, ArrayList<Person> bList) { 
		int timeA = 0, timeB = 0;
		if(aList.size() > 0) timeA = processDown(aList, sList[0][2]);
		if(bList.size() > 0) timeB = processDown(bList, sList[1][2]);
		return timeA>timeB?timeA:timeB;
	}
	
	//해당 리스트의 사람이 계단을 모두 내려가는데 걸리는 시간 계산
	private static int processDown(ArrayList<Person> list, int height) {
		Collections.sort(list);
		int time = list.get(0).time;
		int size = list.size();
		int ingCnt=0,cCnt=0;
		Person p;
		
		while(true) {
			for (int i = 0; i < size; i++) {
				p = list.get(i);
				if(p.status == C) continue;
				if(p.time == time) {
					p.status = W;
				}
				else if(p.status == W && ingCnt < 3) {
					p.status = D;
					p.downCnt = 1;
					ingCnt++;
				}
				else if(p.status == D) {
					if(p.downCnt < height) p.downCnt++;
					else {
						p.status = C;
						cCnt++;
						ingCnt--;
					}
				}
			}
			if(cCnt == size) break;
			++time;
		}
		return time;
	}

}
