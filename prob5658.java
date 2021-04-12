package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//삼성 5658 보물상자 비밀번호
public class prob5658 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int num = N/4;
			
			String tmp = bf.readLine();
			
			ArrayList<Integer> list = new ArrayList<>();
			
			int start = 0;
			for (int i = 0; i < N-1; i++) {
				for (int j = start; j < start+N;) {
					char[] ttemp = new char[num];
					for (int l = 0; l < num; l++) {
						ttemp[l] = tmp.charAt((j+l)%N);
						//System.out.println(l+" "+ttemp[l]);
					}
					String ttmp = new String(ttemp);
					//System.out.println(ttmp);
					int a = Integer.parseInt(ttmp, 16);
					boolean flag = false;
					for (int l = 0; l < list.size(); l++) {
						if(list.get(l)==a) {
							flag = true; 
							break;
						}
					}
					if(!flag) list.add(a);
					j += num;
				}
				start = (start+N-1) % N;
			}
			
			list.sort(Comparator.reverseOrder());
			
			//System.out.println(list);
			System.out.println("#"+t+" "+list.get(K-1));
//			for (int i = 1; i <= size; i++) {
//				if(array[i]) System.out.println(i);
//			}
			
			
		}
		
	}

}
