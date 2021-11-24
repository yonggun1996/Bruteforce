package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2615 {
    static int[][] board = new int[19][19];//바둑판을 표현한 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 19; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 19; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //수직으로 살핀다
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board[i][j] > 0){
                    int count = widthSearch(i,j);
                    if(count == 5){
                        int k = i;
                        //수직 으로 있는 공간 중 제일 윗 공간을 살핀다
                        while(k >= 0 && board[i][j] == board[k][j]){
                            k--;
                        }

                        k++;
                        System.out.println(board[i][j]);
                        System.out.println((k + 1) + " " + (j + 1));
                        return;
                    }
                }
            }
        }

        //수평으로 살핀다
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board[i][j] > 0){
                    int count = heightSearch(i,j);
                    if(count == 5){
                        int k = j;

                        //수평 공간중 제일 오른족을 살핀다
                        while(k >= 0 && board[i][j] == board[i][k]){
                            k--;
                        }

                        k++;
                        System.out.println(board[i][j]);
                        System.out.println((i + 1) + " " + (k + 1));
                        return;
                    }
                }
            }
        }

        //왼쪽위에서 오른쪽 아래
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board[i][j] > 0){
                    int count = left_rightSearch(i,j);
                    if(count == 5){
                        int ni = i;
                        int nj = j;

                        //왼쪽, 위로 인덱스를 빼면서 제일 왼쪽 값을 찾는다
                        while(ni >= 0 && nj >= 0 && board[i][j] == board[ni][nj]){
                            ni--;
                            nj--;
                        }

                        ni++;
                        nj++;

                        System.out.println(board[i][j]);
                        System.out.println((ni + 1) + " " + (nj + 1));
                        return;
                    }
                }
            }
        }

        //오른쪽위에서 왼쪽 아래
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board[i][j] > 0){
                    int count = right_leftSearch(i,j);
                    if(count == 5){
                        int ni = i;
                        int nj = j;

                        //왼족 아래로 인덱스를 늘리고 줄이면서 제일 왼족 값을 확인한다
                        while(ni < 19 && nj >= 0 && board[i][j] == board[ni][nj]){
                            ni++;
                            nj--;
                        }

                        ni--;
                        nj++;

                        System.out.println(board[i][j]);
                        System.out.println((ni + 1) + " " + (nj + 1));
                        return;
                    }
                }
            }
        }

        //4 방향 모두 없는 경우 0을 출력한다.
        System.out.println("0");
    }

    static int widthSearch(int x, int y){
        Queue<BoardLocation> queue = new LinkedList<>();
        queue.offer(new BoardLocation(x,y));
        int count = 0;
        int color = board[x][y];
        boolean[][] visit = new boolean[19][19];
        visit[x][y] = true;

        while(!queue.isEmpty()){
            count++;
            BoardLocation boardlocation = queue.poll();
            int nx = boardlocation.x;
            int ny = boardlocation.y;

            if(nx - 1 >= 0 && board[nx - 1][ny] == color && !visit[nx - 1][ny]){
                queue.offer(new BoardLocation(nx - 1, ny));
                visit[nx - 1][ny] = true;
            }

            if(nx + 1 < 19 && board[nx + 1][ny] == color && !visit[nx + 1][ny]){
                queue.offer(new BoardLocation(nx + 1, ny));
                visit[nx + 1][ny] = true;
            }
        }

        return count;
    }

    static int heightSearch(int x, int y){
        Queue<BoardLocation> queue = new LinkedList<>();
        queue.offer(new BoardLocation(x,y));
        int count = 0;
        int color = board[x][y];
        boolean[][] visit = new boolean[19][19];
        visit[x][y] = true;

        while(!queue.isEmpty()){
            count++;
            BoardLocation boardlocation = queue.poll();
            int nx = boardlocation.x;
            int ny = boardlocation.y;

            if(ny - 1 >= 0 && board[nx][ny - 1] == color && !visit[nx][ny - 1]){
                queue.offer(new BoardLocation(nx, ny - 1));
                visit[nx][ny - 1] = true;
            }

            if(ny + 1 < 19 && board[nx][ny + 1] == color && !visit[nx][ny + 1]){
                queue.offer(new BoardLocation(nx, ny + 1));
                visit[nx][ny + 1] = true;
            }
        }

        return count;
    }

    static int left_rightSearch(int x, int y){
        Queue<BoardLocation> queue = new LinkedList<>();
        queue.offer(new BoardLocation(x,y));
        int count = 0;
        int color = board[x][y];
        boolean[][] visit = new boolean[19][19];
        visit[x][y] = true;

        while(!queue.isEmpty()){
            count++;
            BoardLocation boardlocation = queue.poll();
            int nx = boardlocation.x;
            int ny = boardlocation.y;

            if(ny - 1 >= 0 && nx - 1 >= 0 && board[nx - 1][ny - 1] == color && !visit[nx - 1][ny - 1]){
                queue.offer(new BoardLocation(nx - 1, ny - 1));
                visit[nx - 1][ny - 1] = true;
            }

            if(ny + 1 < 19 && nx + 1 < 19 && board[nx + 1][ny + 1] == color && !visit[nx + 1][ny + 1]){
                queue.offer(new BoardLocation(nx + 1, ny + 1));
                visit[nx + 1][ny + 1] = true;
            }
        }

        return count;
    }

    static int right_leftSearch(int x, int y){
        Queue<BoardLocation> queue = new LinkedList<>();
        queue.offer(new BoardLocation(x,y));
        int count = 0;
        int color = board[x][y];
        boolean[][] visit = new boolean[19][19];
        visit[x][y] = true;

        while(!queue.isEmpty()){
            count++;
            BoardLocation boardlocation = queue.poll();
            int nx = boardlocation.x;
            int ny = boardlocation.y;

            if(ny - 1 >= 0 && nx + 1 < 19 && board[nx + 1][ny - 1] == color && !visit[nx + 1][ny - 1]){
                queue.offer(new BoardLocation(nx + 1, ny - 1));
                visit[nx + 1][ny - 1] = true;
            }

            if(ny + 1 < 19 && nx - 1  >= 0 && board[nx - 1][ny + 1] == color && !visit[nx - 1][ny + 1]){
                queue.offer(new BoardLocation(nx - 1, ny + 1));
                visit[nx - 1][ny + 1] = true;
            }
        }

        return count;
    }
}

class BoardLocation{
    int x; int y;

    public BoardLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
}
