package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 2112 보호 필름
public class prob2112 {

	private static int result,D,W,K;
	
	private static boolean judge(int[][] map) {
		for (int i = 0; i < W; i++) {
			int cnt = 1;
			boolean f = false;
			for (int j = 0; j < D-1; j++) {
				int a = map[j][i];
				int b = map[j+1][i];
				if(a==b) {
					cnt++;
					if(cnt==K) {
						f = true;
						break;
					}
				}
				else cnt = 1;
			}
			//System.out.println(i+" "+f);
			if(!f) return false;
		}
		return true;
	}
	
	private static int[][] copy(int[][] map) {
		int[][] tmp = new int[D][W];
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}
	
	private static void solve(int[][] map,int cnt,int start) {
		//System.out.println(result+" "+cnt);
		if(K < cnt) return;
		if(result == 0) return;
		if(result <= cnt) return;
		//if(array[cnt]) return;
		
		System.out.println(start+" "+result+" "+cnt);
//		for (int i = 0; i < D; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		if(judge(map)) {
			System.out.println("합격! :  "+result+" "+cnt);
			result = cnt;
			//array[cnt] = true;
			return;
		}
		
		if(start == D) return;
		
		int[][] tmp1 = copy(map);
		for (int j = 0; j < W; j++) {
			tmp1[start][j] = 0;
		}
		solve(tmp1,cnt+1,start+1);
		
		int[][] tmp2 = copy(map);
		for (int j = 0; j < W; j++) {
			tmp2[start][j] = 1;
		}
		solve(tmp2,cnt+1,start+1);
		
		solve(map,cnt,start+1);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			
			if(K==1) result = 0;
			else solve(map,0,0);
			//judge(map);
			
			System.out.println("#"+t+" "+result);
		}
		
	}

}
