package sw_typeA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//삼성 4014 활주로 건설
public class prob4014 {

	private static class check {
		int index; boolean bridge;
		check(int index, boolean bridge) {
			this.index = index;
			this.bridge = bridge;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(bf.readLine());
		for (int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			//가로줄 map[i][index]
			for (int i = 0; i < n; i++) {
				check point = new check(0,false);
				int index = 0;
				boolean flag = true;
				while(index < n-1) {
					if(map[i][index]==map[i][index+1]) index++;
					else if(Math.abs(map[i][index]-map[i][index+1])>=2) {
						flag = false;
						break;
					}
					else { //다리를 놓는 경우
						if(map[i][index]>map[i][index+1]) { //지대가 낮아진 경우 
							if(index+1 > n-x) {
								flag = false;
								break;
							} else {
								if(index+2 >= n) {
									flag = false;
									break;
								} 
								else if(index+x == n-1) {
									int tmp = map[i][index+1];
									boolean flagtemp = true;
									for (int j = index+2; j <= index+x; j++) {
										if(map[i][j]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) break;
									else {
										flag = false;
										break;
									}
								}
								else {						
									int tmp = map[i][index+1];
									boolean flagtemp = true;
									for (int j = index+2; j <= index+x; j++) {
										if(map[i][j]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) {
										index = index+x;	
										point = new check(index,true);
									} else {
										flag = false;
										break;
									}
								}
							}
						} 
						else if(map[i][index]<map[i][index+1]) { //지대가 높아진 경우
							if(point.bridge) {
								if(point.index < index-x+1) {
									boolean flagtemp = true;
									int tmp = map[i][index];
									for (int j = index-1; j > index-x; j--) {
										if(map[i][j]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) index++;
									else {
										flag = false;
										break;
									}
								}
								else {
									flag = false;
									break;
								}
							} else {								
								if(index+1 >= x) {
									boolean flagtemp = true;
									int tmp = map[i][index];
									for (int j = index-1; j > index-x; j--) {
										if(map[i][j]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) index++;
									else {
										flag = false;
										break;
									}
								}
								else {
									flag = false;
									break;
								}
							}
						}
					}
				}
				if(flag) {
					//System.out.println("가로"+" "+i);
					result++;
				}
			}
			//세로줄 map[index][i]
			for (int i = 0; i < n; i++) {
				check point = new check(0,false);
				int index = 0;
				boolean flag = true;
				while(index < n-1) {
					if(map[index][i]==map[index+1][i]) index++;
					else if(Math.abs(map[index][i]-map[index+1][i])>=2) {
						flag = false;
						break;
					}
					else { //다리를 놓는 경우
						if(map[index][i]>map[index+1][i]) { //지대가 낮아진 경우 
							if(index+1 > n-x) {
								flag = false;
								break;
							} else {
								if(index+2 >= n) {
									flag = false;
									break;
								} 
								else if(index+x == n-1) {
									int tmp = map[index+1][i];
									boolean flagtemp = true;
									for (int j = index+2; j <= index+x; j++) {
										if(map[j][i]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) break;
									else {
										flag = false;
										break;
									}
								}
								else {						
									int tmp = map[index+1][i];
									boolean flagtemp = true;
									for (int j = index+2; j <= index+x; j++) {
										if(map[j][i]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) {
										index = index+x;	
										point = new check(index,true);
									} else {
										flag = false;
										break;
									}
								}
							}
						} 
						else if(map[index][i]<map[index+1][i]) { //지대가 높아진 경우
							if(point.bridge) {
								if(point.index < index-x+1) {
									boolean flagtemp = true;
									int tmp = map[index][i];
									for (int j = index-1; j > index-x; j--) {
										if(map[j][i]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) index++;
									else {
										flag = false;
										break;
									}
								}
								else {
									flag = false;
									break;
								}
							} else {								
								if(index+1 >= x) {
									boolean flagtemp = true;
									int tmp = map[index][i];
									for (int j = index-1; j > index-x; j--) {
										if(map[j][i]!=tmp) {
											flagtemp = false;
											break;
										}
									}
									if(flagtemp) index++;
									else {
										flag = false;
										break;
									}
								}
								else {
									flag = false;
									break;
								}
							}
						}
					}
				}
				if(flag) {
					//System.out.println("가로"+" "+i);
					result++;
				}
			}
			System.out.println("#"+t+" "+result);
		}
	}

}
