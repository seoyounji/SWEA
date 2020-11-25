package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 4008 숫자 만들기
public class prob4008 {

	private static int ops[],opsCnt[],numbers[],max,min,N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st = null;
		opsCnt = new int[4];
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			numbers = new int[N];
			ops = new int[N-1];
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {
				opsCnt[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			permu(0);
			
			System.out.println("#"+t+" "+(max-min));
		}
	}
	
	// +:0 -:1 *:2 /:3
	private static void permu(int cnt) { //연산자의 순열 생성
		if(cnt == N-1) {
			cal();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(opsCnt[i] == 0) continue;
			ops[cnt] = i;
			opsCnt[i]--;
			permu(cnt+1);
			opsCnt[i]++;
		}
	}
	
	private static void cal() { //순열의 상태에 따른 수식 값 계산 결과
		int result = numbers[0];
		for (int i = 1; i < N; i++) {
			int currNumber = numbers[i];
			switch(ops[i-1]) {
			case 0:
				result += currNumber;
				break;
			case 1:
				result -= currNumber;
				break;			
			case 2:
				result *= currNumber;
				break;			
			case 3:
				result /= currNumber;
				break;		
			}
		}
		max = Math.max(max, result);
		min = Math.min(min, result);
	}

}
