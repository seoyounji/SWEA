package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 8382 방향 전환
public class prob8382 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x = Math.abs(x1 - x2);
			int y = Math.abs(y1 - y2);
			int result = 0;
			if(x%2==0 && y%2==0) result = (x>=y) ? x+x:y+y;
			else if(x%2==1 && y%2==1) result = (x>=y) ? x+x:y+y;
			else if(x%2==0 && y%2==1) result = (x>y) ? x+x-1:y+y-1;
			else if(x%2==1 && y%2==0) result = (x>y) ? x+x-1:y+y-1;
			System.out.println("#"+t+" "+result);
		}
	}

}
