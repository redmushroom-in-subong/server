# RMS_SERVER
Red Mushroom in Subong



# git 사용 규칙
0. 본인 토큰을 잘 보관해야함 profile image/setting/develop option ~ token
1. master 에서 직접 작업하지 않는다 (master로 바로 push 하지 않는다)

# git push/pull
#### 0. (intelliJ) 로컬 터미널에서 clone 받은 경로로 이동
#### 1. master brandch를 pull 받는다
```
git pull origin master
```

<br/>

#### 2. 작업 후 커밋한다
```
git add . && git commit -m "commit message"
```

<br/>

#### 3. 작업에 해당하는 부분 (커밋한 부분) 을 새로운 branch에 push한다 - branchname : 본인 이니셜/작업일자+작업시간
* 방법1)
```
git push origin HEAD:yts/01041725
```
경고가 뜨면서 안되는 경우 시키는대로 명령어를 다르게 입력 또는 방법2) 
* 방법2)
```
git checkout -b yts/0104 && git push origin yts/0104
```

<br/>

#### 4. web ide에서 ( 여기서 ) merge한다. -- 한명이 맡아서 해야할듯 --

  - merge 한 사람은 톡방에 merge 했다고 알리면 다른사람들은 pull을 받는다 (#1)
  - push한 경우 홈으로 가면 초록색 버튼 나오는데 그 버튼 눌러서 잘 하면됨 -> merge후 브런치 삭제(삭제하겠냐고 나옴)
   
<br/>

#### 5. 충돌이 날경우 - 본인이 push한 브랜치가 merge되지 않는 경우

  -  master에 직접 push 하지 않는 선에서 잘 해결하기 (2번처럼 무식하게 하기 싫은경우)
  -  작업한 부분 - 파일명 기록, 백업(#7) 해놓은후 다 삭제하고 다시 clone 받고 반영해서 올리기
  
<br/>

#### 6. pull 받았는데 충돌이 난 경우 - (#5)보다는 나은 상황

   - intelliJ ide 에서 적절히 병합하기
    accept [current change(내 변경) vs incomming change(마스터의 변경)] 하게 해주는게 있음

   - 또는 충돌나는 파일명 기록해두고 pull 받기 이전으로 돌아가고 pull 부터 재대로 받기

   ```
   git reflog
   ```
   ![image](https://user-images.githubusercontent.com/38269178/148190619-faa2b615-9526-424f-b9b0-7c30fc3856ca.png)
   ```
   git reset --hard {{돌아가고자 하는 시점의 노란색 해시코드 입력 - 이거는 web에 있는 history 에 붙은 해시코드도 입력가능}}
   ```
   
~ 기록해둔 파일 삭제후 다시 pull 받고 그 위에 작업

<br/>

***
**#5,#6 의 상황은  작업 파일이 겹치는 경우에 pull을 받지 않고 푸시를 한 경우(or push를 늦게해서 어쩔수없이 충돌나는 경우) 입니다.**
**작업 영역이 겹치게 될때는 담당자와 협의후 작업진행 합시다 :)**
  
***
#### 7. 백업

  - 본인 공간에 복붙하기
  - 본인 로컬 git 에 저장
    ```
    git branch (branch name) && git checkout (branch name)
    ```
    
<br/>


