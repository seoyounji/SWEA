package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//삼성 8275 햄스터
public class prob8275 {

	private static int N,X,M,sum;
	private static ArrayList<point> list;
	private static int[] cage,result;
	
	private static class point{
		int start; int end; int sum;
		point(int start,int end,int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
		}
	}
	
	private static void rePermu(int idx) {
		if(idx == N) {
			if(calsum(cage) <= sum) return;
			solve(cage);
			return;
		}
		for (int i = 0; i <= X; i++) {
			cage[idx] = i;
			rePermu(idx+1);
		}
	}
	
	private static int calsum(int[] array) {
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			tmp += array[i];
		}
		return tmp;
	}
	
	private static void solve(int[] temp) {
		for (int j = 0; j < M; j++) {
			int tt = 0;
			for (int k = list.get(j).start; k <= list.get(j).end; k++) {
				tt += temp[k-1];
			}
			if(tt != list.get(j).sum) return;
		}
		sum = calsum(temp);
		for (int i = 0; i < N; i++) {
			result[i] = temp[i];
		}
		return;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			sum = -1;
			cage = new int[N];
			result = new int[N];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int sum = Integer.parseInt(st.nextToken());
				list.add(new point(start,end,sum));
			}
			
			rePermu(0);
			
			if(sum==-1) {
				System.out.println("#"+t+" -1");
			}
			else {
				System.out.print("#"+t);
				for (int i = 0; i < N; i++) {
					System.out.print(" "+result[i]);
				}
				System.out.println();
			}
			
		}
	}

}
