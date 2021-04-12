package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 5643 키 순서
public class prob5643 {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] list = new boolean[N+1][N+1];
        boolean[][] listreverse = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a][b] = true;
            listreverse[b][a] = true;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int j2 = 1; j2 <= N; j2++) {
                    if(list[j][i] && list[i][j2]) list[j][j2] = true;
                    if(listreverse[j][i] && listreverse[i][j2]) listreverse[j][j2] = true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                list[i][j] = list[i][j] | listreverse[i][j];
            }
        }
        int result = 0;
        outer:
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i==j) continue;
                if(!list[i][j]) continue outer;
            }
            result++;
        }
        System.out.println(result);
	}
}
