package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 5644 무선 충전
public class prob5644 {

	private static class charge {
		int x; int y; int c; int p;
		charge(int x,int y,int c,int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int time = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			Queue<Integer> a = new LinkedList<Integer>();
			a.offer(0);
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < time; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				a.offer(tmp);
			}
			Queue<Integer> b = new LinkedList<Integer>();
			b.offer(0);
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < time; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				b.offer(tmp);
			}
			ArrayList<charge> list = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				charge tmp = new charge(x,y,c,p);
				list.add(tmp);
			}
			int result = 0;
			int a_nowX = 1;
			int a_nowY = 1;
			int b_nowX = 10;
			int b_nowY = 10;
			int[] dx = {0,-1,0,1,0};
			int[] dy = {0,0,1,0,-1};
			for (int i = 0; i <= time; i++) {
				int tempa = a.poll();
				a_nowX += dx[tempa];
				a_nowY += dy[tempa];
				int tempb = b.poll();
				b_nowX += dx[tempb];
				b_nowY += dy[tempb];
				int total = 0;
				int tta = 0;
				int ttb = 0;
				
				for (int j = 0; j < num; j++) {
					tta = 0;
					charge tmp = list.get(j);
					int distanceA = Math.abs(tmp.y-a_nowX)+Math.abs(tmp.x-a_nowY);
					if(distanceA <= tmp.c) tta = tmp.p;
					
					for (int k = 0; k < num; k++) {
						ttb = 0;
						charge temp = list.get(k);
						int distanceB = Math.abs(temp.y-b_nowX)+Math.abs(temp.x-b_nowY);
						if(distanceB <= temp.c) {
							if(tta!=0) {
								if(j!=k) ttb = temp.p;
							}
							else {
								ttb = temp.p;
							}
						}
						total = Math.max(total, tta+ttb);
					}
				}
				result += total;
			}
			System.out.println("#"+t+" "+result);
		}
	}

}
