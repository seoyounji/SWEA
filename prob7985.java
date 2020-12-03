package ComputingThinking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//삼성 7985 Rooted Binary Tree 재구성
public class prob7985 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= test_case; i++) {
			int k = Integer.parseInt(bf.readLine());
			int[] array = new int[(int)(Math.pow(2, k)-1)];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < array.length; j++) {
				array[j] = Integer.parseInt(st.nextToken());
			}
			Queue<Integer> queue = new LinkedList<>();
			//System.out.println(array.length);
			queue.offer(array.length/2);
			System.out.println("#"+i+" "+array[array.length/2]);
			for (int j = 1; j < k; j++) {
				int num = (int)Math.pow(2, j-1);
				int index = queue.peek()/2+1;
				for (int l = 1; l <= num; l++) {
					int number = queue.poll();
					queue.offer(number-index);
					queue.offer(number+index);
					//System.out.println((number-index)+" "+(number+index));
					System.out.print(array[number-index]+" "+array[number+index]+" ");					
				}
				System.out.println();
			}
			
		}
		
		
	}

}
