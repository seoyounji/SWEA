package Algorithm_0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//삼성 1251 하나로 using Kruscal
public class prob1251_Kruscal {

	private static int[] x;
	private static int[] y;
	private static int[] parent;
	private static int N;
	private static double result;
	
	public static class point implements Comparable<point>{
		double weight; int from; int to;
		point(int from,int to,double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(point o) {
			if(this.weight==o.weight) {
				if(this.from==o.from) {
					return Integer.compare(this.to, o.to);
				}
				return Integer.compare(this.from, o.from);
			}
			return Double.compare(this.weight, o.weight);
		}
	}
	private static double distance(int x1,int y1,int x2,int y2) {
		return (double)(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
	}
	private static int find(int x) {
		if(parent[x]==x) return x;
		else {
			return parent[x] = find(parent[x]);
		}
	}
	private static boolean union(int x,int y) {
		if(find(x)==find(y)) return false;
		parent[find(y)] = find(x);
		return true;
	}
	private static void Kruscal(PriorityQueue<point> queue) {
		int cnt = 0;
		while(!queue.isEmpty()) {
			point current = queue.poll();
			if(cnt==N-1) break;
			if(union(current.from,current.to)) {
				cnt++;
				result += current.weight;
			}
		}
//		for(point current : queue) {
//			if(cnt==N-1) break;
//			if(union(current.from,current.to)) {
//				cnt++;
//				result += current.weight;
//			} else continue;
//		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= TestCase; i++) {
			N = Integer.parseInt(bf.readLine());
			result = 0;
			x = new int[N];
			y = new int[N];
			parent = new int[N];
			for (int j = 0; j < N; j++) {
				parent[j]=j;
			}
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int k = 0; k < N; k++) {
				x[k] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int k = 0; k < N; k++) {
				y[k] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(bf.readLine());
			//class를 만든뒤 weight를 계산하고 그걸 priorityQueue에 넣어서 정렬
			//ArrayList<point> list = new ArrayList<point>();
			PriorityQueue<point> list = new PriorityQueue<point>();
			
			for (int j = 0; j < N-1; j++) {
				for (int j2 = j+1; j2 < N; j2++) {
					double tmp = distance(x[j],y[j],x[j2],y[j2]);
					//list.add(new point(j,j2,tmp));
					list.offer(new point(j,j2,tmp));
				}
			}
			//Collections.sort(list);
			Kruscal(list);
			long tmp = (long) Math.round(result*E);
			System.out.println("#"+i+" "+tmp);
		}
	}
}
