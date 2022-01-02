# RMS_SERVER
Red Mushroom in Subong


# git 사용 규칙
0. 본인 토큰을 잘 보관해야함 profile image/setting/develop option ~ token
1. master 에서 직접 작업하지 않는다 (master로 바로 push 하지 않는다)

**1. 작업 시작 전에 master branch에서 pull 을 받는다(local intelliJ 터미널에서)**

```
git pull origin master
```

**2. 작업이 끝나면 이니셜/날짜시간 이라는 브랜치를 만들어 푸쉬한다**
pull을 한번 받으면 충돌이 날지 안날지 알수있다.
```
git pull origin master&&
git push origin HEAD:[이니셜/날짜시간 (ex.yts/12262357)]
```

**3. 작업된 브랜치와 master를 병합하고 작업 브랜치를 제거한다**
```
병합은 web에서 진행한다
누가 push 하면 merge request 가 나오는데 그거 들어가서 
```
**4. 만약 작업 브랜치와 master가 충돌이 날때 (자동 병합이 안될때)**

```
(로컬 터미널에서)
 1. 로컬 터미널로 가서 master branch pull을 받는다.
 2. 그러면 자동 병합 실패 메세지와 충돌나는 부분을 알려준다.
 3. 수동으로 병합한다
(WEB IDE에서)
 1. 알아보자.
```

# AWS 접속방법
**1. 키페어를 다운받는다**
- ~에 있음

**2. aws 로그인 후 ec2에 접속하여 인스턴스 실행중인지 확인한다 - 계속 켜놓긴 할거라 생략 가능할듯**

**3. 다음 과정 진행** 
```
인스턴스 ID i-0e930ad9667d1f30a
1.SSH 클라이언트를 엽니다.
2.프라이빗 키 파일을 찾습니다. 이 인스턴스를 시작하는 데 사용되는 키는 rms-ec2-key.pem입니다.
  필요한 경우 이 명령을 실행하여 키를 공개적으로 볼 수 없도록 합니다.
  chmod 400 rms-ec2-key.pem
3.퍼블릭 DNS을(를) 사용하여 인스턴스에 연결: ** 키페어가 있는 디렉토리로 이동한 후에 다음 명령어 실행
 ssh -i "rms-ec2-key.pem" ec2-user@ec2-15-164-158-186.ap-northeast-2.compute.amazonaws.com
```
# 서버 배포(AWS EC2내에서 실행)
**1. 위에 AWS 접속 방법 -- ssh 인증으로 콘솔에 접속**

**2. /home/ec2-user/app/RMS_SERVER/drife-server 로 이동**

```
cd /home/ec2-user/app/RMS_SERVER/drife-server
```
**3. git 에 있는 최신 master 브랜치 반영**

```
git pull origin master
```
**4. 배포할 버전 빌드 - 아직 이름 붙이는건 못함**

```
./gradlew build
```
**권한 관련 문제 발생시**
```
sudo chmod 777 ./gradlew && ./gradlew build
```
**5. 빌드 성공 후**

```
cd build/libs && ls
```
보이는 파일중
drife-server-0.0.1-SNAPSHOT-plain.jar  drife-server-0.0.1-SNAPSHOT.jar 
둘중 뒤에 plain 없는 .jar 실행


# RUN SERVER
**1. 포그라운드 (터미널 닫으면 서버 닫힘)**
```
java -jar drife-server-0.0.1-SNAPSHOT.jar
```
**2. 백그라운드 (터미널 닫아도 서버 유지)**
**- 실행**
```
nohup java -jar build/libs/drife-server-0.0.1-SNAPSHOT.jar &
```
- nohup은 터미널을 꺼도 애플리케이션이 꺼지지 않도록 하는 명령어
- &는 애플리케이션이 백그라운드에서 돌아갈 수 있도록 하는 명령어

 
**백그라운드 로그 확인**
```
tail -f nohup.out
```
**종료시키기 위한 프로세스 아이디 확인**
```
sudo lsof -t -i:8080
```
**백그라운드 실행 종료**
```
kill -9 [프로세스 아이디]
```
