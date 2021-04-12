package Algorithm_0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.PriorityBlockingQueue;

//삼성 1251 하나로 using Prim with PQ
public class prob1251_Prim_usingPriorityQueue {

	private static int N;
	private static long[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= TestCase; i++) {
			N = Integer.parseInt(bf.readLine());
			int[] x = new int[N];
			int[] y = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int k = 0; k < N; k++) {
				x[k] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int k = 0; k < N; k++) {
				y[k] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(bf.readLine());
			adjMatrix = new long[N][N];
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					adjMatrix[j][j2] = adjMatrix[j2][j] = getDistance(x[j],x[j2],y[j],y[j2]);
				}
			}
			System.out.println("#"+i+" "+Math.round(E*makeMST()));
		}
	}
	
	private static long makeMST() {
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		long result = 0; //최소신장트리비용
		int cnt = 0; //처리한 정점 수
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0; //0섬을 시작섬으로
		
		PriorityQueue<vertex> pQueue = new PriorityQueue<vertex>();
		pQueue.offer(new vertex(0,minEdge[0]));
		
		while(true) {
			//1단계 : 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
			vertex minVertex = pQueue.poll();
			
			if(visited[minVertex.no]) continue;
			
			visited[minVertex.no] = true; //정점 방문처리 (신장트리에 포함시킴)
			result += minVertex.cost; //간선비용 누적
			if(++cnt==N) break;
			
			//2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minVertex.no][i]>0 && minEdge[i]>adjMatrix[minVertex.no][i]) {
					minEdge[i] = adjMatrix[minVertex.no][i];
					pQueue.offer(new vertex(i,minEdge[i]));
				}
			}
		}
		return result;
	}
	
	private static long getDistance(int x1,int x2,int y1,int y2) {
		return (long)(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
	}
	
	public static class vertex implements Comparable<vertex> {
		int no;
		long cost;
		vertex(int no,long cost) {
			super();
			this.no = no;
			this.cost = cost;
		}
		@Override
		public int compareTo(vertex o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}
