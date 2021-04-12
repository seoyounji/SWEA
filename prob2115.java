package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 2115 벌꿀채취
public class prob2115 {

	private static int N,M,C;
	private static int[][] map,maxMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#"+t+" "+getMaxBenefit());
		}
	}

	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}

	private static int processCombination() {
		int max = 0, a = 0, b = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				a = maxMap[i][j];
				b = 0;
				for (int j2 = j+M; j2 <= N-M; j2++) {
					b = Math.max(b, maxMap[i][j2]);
				}
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						b = Math.max(b, maxMap[i2][j2]);
					}
				}
				max = Math.max(max, a+b);
			}
		}
		return max;
	}

	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				makeMaxSubset(i,j,0,0,0);
			}
		}
	}

	private static void makeMaxSubset(int i,int j,int cnt,int sum,int powSum) {
		if(sum > C) return;
		if(cnt == M) {
			if(maxMap[i][j-M] < powSum) maxMap[i][j-M] = powSum;
			return;
		}
		makeMaxSubset(i,j+1,cnt+1,sum+map[i][j],powSum+(map[i][j]*map[i][j]));
		makeMaxSubset(i,j+1,cnt+1,sum,powSum);
	}

}
