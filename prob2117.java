package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//삼성 2117 홈 방범 서비스
public class prob2117 {

	private static ArrayList<point> list;
	private static int result,M;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result=0;
			list = new ArrayList<>();
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(bf.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp==1) list.add(new point(j,j2));
				}
			}
			for (int k = 1; k <= N+1; k++) {
				for (int j = 0; j < N; j++) {
					for (int j2 = 0; j2 < N; j2++) {
						search(j,j2,k);
					}
				}
			}
			System.out.println("#"+i+" "+result);
		}
	}
	private static void search(int j, int j2, int k) {
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			int dist = Math.abs(j-list.get(i).x) + Math.abs(j2-list.get(i).y);
			if(dist<=k-1) cnt++;
		}
		int pay = cnt*M;
		int cost = (k*k)+((k-1)*(k-1));
		if(cost<=pay) result = Math.max(result, cnt);
	}

}
