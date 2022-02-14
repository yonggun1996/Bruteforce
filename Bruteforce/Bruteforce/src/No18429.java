package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No18429 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int n;
    static int k;
    static int[] kit_Arr;//입력받은 키트들
    static boolean[] use;//해당 키트를 사용했는지 확인하는 배열
    static int answer = 0;//정답이 될 변수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            list.add(Integer.parseInt(st.nextToken()));
        }

        kit_Arr = new int[list.size()];
        use = new boolean[list.size()];

        //완전탐색을 실시하는 첫 for문
        //키트 배열에 중량을 추가하고
        //해당 키트를 사용했음으로 초기화한다
        for(int i = 0; i < list.size(); i++){
            kit_Arr[0] = list.get(i);
            use[i] = true;
            set_Answer(1);
            use[i] = false;
            kit_Arr[0] = 0;
        }

        System.out.println(answer);
    }

    //키트 배열의 인덱스를 매개변수로 재귀호출을 하는 메서드
    //모든 키트들을 사용했으면 사용하면서 중량이 500 밑으로 내려갔는지 확인한다
    //내려간 경우가 생기면 탐색을 중단하고 그렇지 않으면 정답 변수를 늘린다다
    static void set_Answer(int index){
        if(index == kit_Arr.length){
            int weight = 500;
            boolean success = true;

            for(int i = 0; i < kit_Arr.length; i++){
                weight += kit_Arr[i];
                weight -= k;

                if(weight < 500){
                    success = false;
                    break;
                }
            }

            if(success){
                answer++;
            }
        }else{
            for(int i = 0; i < list.size(); i++){
                if(!use[i]){
                    kit_Arr[index] = list.get(i);
                    use[i] = true;
                    set_Answer(index + 1);
                    use[i] = false;
                    kit_Arr[index] = 0;
                }
            }
        }
    }
}
