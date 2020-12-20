package Algorithm_0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//삼성 1251 하나로 using Prim
public class prob1251_Prim {

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
		
		while(true) {
			//1단계 : 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0; //최소 간선비용의 정점
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			visited[minNo] = true; //정점 방문처리 (신장트리에 포함시킴)
			result += min; //간선비용 누적
			if(++cnt==N) break;
			
			//2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여 minEdge 업데이트
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minNo][i]>0 && minEdge[i]>adjMatrix[minNo][i]) {
					minEdge[i] = adjMatrix[minNo][i];
				}
			}
		}
		return result;
	}
	
	private static long getDistance(int x1,int x2,int y1,int y2) {
		return (long)(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
	}
	
}
