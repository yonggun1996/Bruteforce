# 브루트포스 알고리즘

## 브루트포스 다른 말로 무차별 대입 공격은 특정한 암호를 풀기 위해 가능한 경우의 수를 모두 대입해보는 것을 의미합니다.
## 이번 알고리즘에서는 어떠한 답을 찾기 위해 A 부터 Z 까지 모든 경우의 수를 대입해보는 문제들이 츨제되었습니다.
-출처 : https://ko.wikipedia.org/wiki/%EB%AC%B4%EC%B0%A8%EB%B3%84_%EB%8C%80%EC%9E%85_%EA%B3%B5%EA%B2%A9

## 백트래킹과 브루트포스의 차이점

-백트래킹은 이미 지나쳐 온 곳을 다시 돌아와서 다른 가능성을 시도해 보는 기법입니다. 예를 들어 8-Queen 문제에서는 말을 놓을 수 있으면 계속 위로 향하지만 윗 부분에서 더이상 놓을 수 없을 경우
 이전 단계로 돌아와 다른 가능성을 찾아보는 방법입니다.
 
-브루트포스는 아까 이야기했던 대로 모든 경우의 수를 다 대입해보는 방법입니다.
 
-출처 :  https://www.acmicpc.net/board/view/20320

1. 블랙잭(2798번 문제)
이 문제는 카드의 숫자와 넘지 않아야 할 한계점이 주어질 때 세 장의 카드를 뽑았을 경우 한계점에 최대한 가까운 수는 무엇인지 구하는 문제입니다.

예시로 한계값은 21, 각 카드는 5,6,7,8,9가 주어질 때 5+6+7, 5+6+8, 5+6+9... 이런식으로 모든 경우의 수를 더해봅니다. 만약 더했을 때 한계값이 나올 경우 프로그램을 종료시키는 알고리즘을
구현했습니다.


2. 분해합(2231번 문제)
예를들어 245라는 숫자가 주어질 때 245 + 2 + 4 + 5 = 256이 됩니다. 이때 245의 분해하븐 256이라고 볼 수 있습니다. 어떠한 자연수 N이 주어질 때 N의 가장 작은 생성자를 구하는 프로그램을 짜야합니다.

저는 이 문제를 1부터 분해합을 시도해 해당 분해합과 똑같다면 반복문을 빠져나와 답을 출력해 내는 알고리즘을 구현했습니다. 단 분해합이 주어진 수 보다 같거나 크면 반목문을 빠져나가 0을 출력합니다.


3. 덩치(7568번 문제)
이 문제는 사람들의 키와 몸무게가 주어질 때 몸무게와 키가 큰 사람부터 순의가 오름차순 출력하는 문제입니다. 이 문제는 for문으로 이용해 다른 사람보다 키도 크고 몸무게도 많이 나간다면
rank를 하나 늘리는 방식으로 문제를 풀었습니다.


4. 체스판 다시 칠하기(1018번 문제)
이 문제는 특정 크기의 체스판이 주어지고 인접한 곳에 같은 색깔이 칠해져있지 않다는 규칙이 주어지고 8*8 체스판을 따로 떼어내서 만들어야 할 때 최소로 색을 바꾸는 경우는 몇 번인지 출력하는
문제입니다.

이 문제는 함수를 하나 더 만들어 입력받은 체스판에서 8*8을 떼어내 흰 색이 먼저 올 경우 몇 번만 고치면 되는지, 검정색이 먼저 올 경우 몇 번 고쳐야 되는지 비교해 가장 작은 경우의 수를
답으로 내놓습니다.


5. 영화감독 숌
이 문제는 영화의 시리즈 별로 666이 꼭 들어가게 하는 알고리즘을 짜서 정수가 주어질 때 그 시리즈의 영화제목은 무엇인지 출력하는 문제입니다.

1번째는 666, 2번째는 1666, 3번째는 2666... 이런식으로 하나하나 대입을 하는 방안을 생각해야 했습니다. 반복문을 이용해 처음은 666으로 세팅을 해 놓습니다. 666에서 수를 하나씩 늘려가면서
중간중간마다 666이 껴 있는지 확인을 합니다. 반복문을 돈 횟수가 입력한 정수와 같다면 반복문을 빠져나와 영화 제목을 출력합니다.