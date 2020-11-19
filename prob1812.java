package sw_typeAplus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//삼성 1812 수정이의 타일 자르기
public class prob1812 {

	private static class rec {
		int w; int h;
		rec(int w,int h) {
			this.w = w;
			this.h = h;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				temp = (int)Math.pow(2, temp);
				list.add(temp);
			}
			int result = 1;
			list.sort(Comparator.reverseOrder());
			
			ArrayList<rec> tile = new ArrayList<>();
			tile.add(new rec(M,M));
			
			for (int a = 0; a < N; a++) {
				boolean flag = false;
				int tmp = list.get(a);
				int size = tile.size();
				for (int i = 0; i < size; i++) {
					rec temp = tile.get(i);
					if(temp.w>=tmp && temp.h>=tmp) {
						if(temp.h > tmp && tmp>0) tile.add(new rec(tmp,temp.h-tmp));
						if(temp.w > tmp && temp.h>0) tile.add(new rec(temp.w-tmp,temp.h));
						tile.remove(i);
						flag = true;
						break;
//						for (int j = 0; j < tile.size(); j++) {
//							System.out.println(a+" "+tile.get(j).w + " " + tile.get(j).h);
//						}
					}
				}
				if(!flag) {
					result++;
					tile.add(new rec(tmp,M-tmp));
					tile.add(new rec(M-tmp,M));
				}
			}
			
			System.out.println("#"+t+" "+result);
		}
		
	}

}
