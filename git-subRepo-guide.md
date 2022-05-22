# git sub repo 사용법

### 사용하는 이유

- DB url,id,pw , kakao id 등 정보를 숨기기 위함
- 동시에 main repo(server)를 public 으로 전환하기 위함

### 느낌

- yml 을 가지고 있는 sub repo(server_secret_info) 를 main repo(server) 에 import해서 사용하는 
그런 느낌
- 이때 sub repo는 private 로 우리만 볼 수 있게 됨
- 자세한 사항은 다음 링크 참고 [https://my-codinglog.tistory.com/30](https://my-codinglog.tistory.com/30)

## 사용법

### 1. pull 또는 clone 받은 이후 , 또는 sub repo가 수정됐을 때

```java
$ git submodule init #init은 pull,clone 받은 초기에만 사용
$ git submodule update --remote
```

** 문제시 처음부터 clone 받고 위 명령어 실행

### 2. 본인이 sub repo 를 수정했을 때
**main repository (server) 는 sub repository(server_secret_info) 의 commit id 를 참조한다.
여기서 sub repo의 수정사항을 push 하고 main repo 수정사항을 push 해야 
main repo 의 sub repo 의 commit id에 대한 참조가 올바르게 된다.**

```java
$ git submodule update --remote --merge # pull 받는거랑 비슷함
$ cd {sub_repo path(server/src/main/resource/server_secret_info)}
$ git pull origin ( 이렇게 하면 첫번째 명령어 안해도 되긴 함 )

#이후 main repo 에서 하던 대로 브랜치 만들어서 푸시하기
```
