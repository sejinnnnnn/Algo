import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * . 평지(전차가 들어갈 수 있다.)
 * * 벽돌로 만들어진 벽
 * # 강철로 만들어진 벽
 * - 물(전차는 들어갈 수 없다.)
 * ^ 위쪽을 바라보는 전차(아래는 평지이다.)
 * v 아래쪽을 바라보는 전차(아래는 평지이다.)
 * < 왼쪽을 바라보는 전차(아래는 평지이다.)
 * > 오른쪽을 바라보는 전차(아래는 평지이다.)
 * 
 * @author SSAFY
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int rowSize = Integer.parseInt(st.nextToken());
			int colSize = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[rowSize][colSize];
			
			int tankRow = -1;
			int tankCol = -1;
			
			for (int i = 0; i < rowSize; i++) {
				String line = br.readLine();
				for (int j = 0; j < colSize; j++) {
					map[i][j] = line.charAt(j);
					
					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {
						tankRow = i;
						tankCol = j;
//						System.out.println(i + ", " + j + ", " + map[i][j]);
					}
					
				}
				
			}
			
			int cmdSize = Integer.parseInt(br.readLine());
			String commands = br.readLine();
			
			for (int i = 0; i < cmdSize; i++) {
				
				char curCommand = commands.charAt(i);
				
				if (curCommand == 'U') {
					
					if (tankRow - 1 < 0 || map[tankRow - 1][tankCol] == '-' || map[tankRow - 1][tankCol] == '*' || map[tankRow - 1][tankCol] == '#') {
						map[tankRow][tankCol] = '^';
//						System.out.println("UP : " + tankRow + ", " + tankCol);
						continue;
					}

					map[tankRow--][tankCol] = '.';
					map[tankRow][tankCol] = '^';
						
//					System.out.println("UP : " + tankRow + ", " + tankCol);
					
				} else if (curCommand == 'D') {
					
					if (tankRow + 1 >= rowSize || map[tankRow + 1][tankCol] == '-' || map[tankRow + 1][tankCol] == '*' || map[tankRow + 1][tankCol] == '#') {
						map[tankRow][tankCol] = 'v';
//						System.out.println("DOWN : " + tankRow + ", " + tankCol);
						continue;
					}

					map[tankRow++][tankCol] = '.';
					map[tankRow][tankCol] = 'v';
					
//					System.out.println("DOWN : " + tankRow + ", " + tankCol);
					
				} else if (curCommand == 'L') {
					
					if (tankCol - 1 < 0 || map[tankRow][tankCol - 1] == '-' || map[tankRow][tankCol - 1] == '*' || map[tankRow][tankCol - 1] == '#') {
						map[tankRow][tankCol] = '<';
//						System.out.println("LEFT : " + tankRow + ", " + tankCol);
						continue;
					}
					
					map[tankRow][tankCol--] = '.';
					map[tankRow][tankCol] = '<';
						
//					System.out.println("LEFT : " + tankRow + ", " + tankCol);
					
				} else if (curCommand == 'R') {
					
					if (tankCol + 1 >= colSize || map[tankRow][tankCol + 1] == '-' || map[tankRow][tankCol + 1] == '*' || map[tankRow][tankCol + 1] == '#') {
						map[tankRow][tankCol] = '>';
//						System.out.println("RIGHT : " + tankRow + ", " + tankCol);
						continue;
					}

					map[tankRow][tankCol++] = '.';
					map[tankRow][tankCol] = '>';
					
//					System.out.println("RIGHT : " + tankRow + ", " + tankCol);
					
				} else if (curCommand == 'S') {
					
					char tankDirection = map[tankRow][tankCol];
					
					if (tankDirection == '^') {
						
						for (int j = tankRow - 1; j >= 0; j--) {
							
							if (map[j][tankCol] == '*') {
								map[j][tankCol] = '.';
//								System.out.println("UP SHOOT : " + j + ", " + tankCol + " => " + map[j][tankCol]);
								break;
							}
							
							if (map[j][tankCol] == '#') {
//								System.out.println("UP SHOOT : " + j + ", " + tankCol + " => " + map[j][tankCol]);
								break;
							}
							
						}
						
					} else if (tankDirection == 'v') {
						
						for (int j = tankRow + 1; j < rowSize; j++) {
							
							if (map[j][tankCol] == '*') {
								map[j][tankCol] = '.';
//								System.out.println("DOWN SHOOT : " + j + ", " + tankCol + " => " + map[j][tankCol]);
								break;
							}
							
							if (map[j][tankCol] == '#') {
//								System.out.println("DOWN SHOOT : " + j + ", " + tankCol + " => " + map[j][tankCol]);
								break;
							}
							
						}
						
					} else if (tankDirection == '<') {
						
						for (int j = tankCol - 1; j >= 0; j--) {
							
							if (map[tankRow][j] == '*') {
								map[tankRow][j] = '.';
//								System.out.println("LEFT SHOOT : " + tankRow + ", " + j + " => " + map[tankRow][j]);
								break;
							}
							
							if (map[tankRow][j] == '#') {
//								System.out.println("LEFT SHOOT : " + tankRow + ", " + j + " => " + map[tankRow][j]);
								break;
							}
							
						}
						
					} else if (tankDirection == '>') {
						
						for (int j = tankCol + 1; j < colSize; j++) {
							
							if (map[tankRow][j] == '*') {
								map[tankRow][j] = '.';
//								System.out.println("RIGHT SHOOT : " + tankRow + ", " + j + " => " + map[tankRow][j]);
								break;
							}
							
							if (map[tankRow][j] == '#') {
//								System.out.println("RIGHT SHOOT : " + tankRow + ", " + j + " => " + map[tankRow][j]);
								break;
							}
							
						}
						
					}
					
				}
				
				
			}
			
			answer.append(String.format("#%d ", t));
			
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < colSize; j++) {
					answer.append(map[i][j]);
				}
				answer.append("\n");
			}

		}
		
		System.out.println(answer.toString().trim());
		
		
	}

}