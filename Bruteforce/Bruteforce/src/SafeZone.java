import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafeZone {

    /*
    비가 오지 않을 수도 있다. 0부터 최대 강수량 까지 최대로 이어진 지역이 답이 된다.
    비가 0부터 최대 건물높이 까지 쏟아지는 경우를 다 확인해야 한다
    해당 위치에서 더 갈 수 있는 지역은 BFS로 탐색한다
     */

    static boolean[][] table;//방문 여부 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //주어진 입력대로 입력
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        int max = 0;

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] > max) max = board[i][j];
            }
        }

        int answer = 0;
        for(int i = 0; i <= max; i++){
            table = new boolean[n][n];//방문여부 테이블을 다시 생성
            set_table(i, board);//건물이 잠기는지 안잠기는지 설정하는 메소드 호출
            int count = 0;//안 잠긴 지역 확인하는 변수
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(table[j][k]){//잠기지 않은 구역
                        search(j,k);//그 구역을 토대로 확인
                        count++;
                    }
                }
            }

            answer = Math.max(count, answer);
        }

        System.out.println(answer);
    }

    //강수량 보다 높은 건물은 잠기지 않게 하는 메소드
    public static void set_table(int rain, int[][] board){
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[0].length; j++){
                if(board[i][j] > rain) table[i][j] = true;
            }
        }
    }

    //잠기지 않은 건물을 기준으로 잠기지 않은 영역을 넓혀가는 BFS 메소드
    public static void search(int i, int j){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(i, j));
        table[i][j] = false;

        while(!queue.isEmpty()){
            Node n = queue.poll();
            int q_i = n.i;
            int q_j = n.j;


            if(q_i > 0 && table[q_i - 1][q_j]){//위쪽 지역
                queue.offer(new Node(q_i - 1, q_j));
                table[q_i - 1][q_j] = false;
            }

            if(q_i < table.length - 1 && table[q_i + 1][q_j]){//아래쪽 지역
                queue.offer(new Node(q_i + 1, q_j));
                table[q_i + 1][q_j] = false;
            }

            if(q_j > 0 && table[q_i][q_j - 1]){//왼쪽 지역
                queue.offer(new Node(q_i, q_j - 1));
                table[q_i][q_j - 1] = false;
            }

            if(q_j < table.length - 1 && table[q_i][q_j + 1]){//오른쪽 지역
                queue.offer(new Node(q_i, q_j + 1));
                table[q_i][q_j + 1] = false;
            }
        }
    }

}

//방문해보려는 노드를 담은 클래스
class Node{
    int i;
    int j;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }
}
