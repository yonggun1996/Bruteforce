import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MakePassword {

    static ArrayList<String> password_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        ArrayList<String> alpahbet = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            alpahbet.add(st.nextToken());
        }
        Collections.sort(alpahbet);//비밀번호 순서도 오름차순이어야 하기 때문에 정렬

        for(int i = 0; i < C; i++){//C까지 해줘야 그 뒤 문자열도 붙일 수 있다.
            StringBuilder sb = new StringBuilder(alpahbet.get(i));
            search_Password(L, i + 1, sb, alpahbet);//백트래킹 메서드 호출
        }

        Collections.sort(password_list);//출력할 때도 정렬이 되어야 한다
        for(int i = 0; i < password_list.size(); i++){
            System.out.println(password_list.get(i));
        }
    }

    //만들어야할 단어의 길이, 탐색을 시작해야할 인덱스, 문자들을 붙인 결과, 입력에 따라 정렬된 알파벳 리스트
    public static void search_Password(int max_len, int index, StringBuilder sb, ArrayList<String> alphabet){
        if(sb.length() == max_len){
            int consonant = 0;//자음개수
            int vowel = 0;//모음개수

            for(int i = 0; i < sb.length(); i++){
                if(sb.charAt(i) == 'a' || sb.charAt(i) == 'e' || sb.charAt(i) == 'i' || sb.charAt(i) == 'o' || sb.charAt(i) == 'u'){
                    vowel++;
                }else{
                    consonant++;
                }
            }

            if(vowel >= 1 && consonant >= 2){
                //문제 조건중 자음 2개 이상, 모음 1개 이상이어야 한다
                password_list.add(sb.toString());
            }
        }else{
            for(int i = index; i < alphabet.size(); i++){
                sb.append(alphabet.get(i));//i번째 문자 붙이기
                search_Password(max_len, i + 1, sb, alphabet);//재귀적으로 호출
                sb.deleteCharAt(sb.length() - 1);//함수 호출이 끝나면 마지막 단어를 지운다
            }
        }
    }

}
