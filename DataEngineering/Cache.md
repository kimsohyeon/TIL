# Cache
Google Guava Cache 라이브러리를 사용  

## 비동기 캐시
scala future를 이용하여 처리할 수 있다.

## 캐시에서 데이터 삭제
메모리를 효율적으로 쓰기 위해서 안쓰는 데이터는 삭제해야됨

### cache replacement

- FIFO(First In First Out) : 오래된 캐시를 먼저 비우고 새로운 캐시를 추가하는 방식이다.
- LIFO(Last In First Out) : 가장 최근에 반영된 캐시가 먼저 지워진다.
- LRU(Least Recently Used) : 가장 최근에 사용되지 않는 순서대로 캐시를 교체한다. 가장 오랫동안 사용되지 않은 캐시가 삭제되며 일반적으로 사용되는 방식이다.
- MRU(Most Recently Used) : 가장 최근에 많이 사용되는 순서대로 캐시를 교체한다. 휘발성 메모리를 이용해야 하는 특수한 상황에 사용된다.
- Random : 말그대로 랜덤으로 캐시를 교체한다.


### Eviction, Expiration, Passivation
- Eviction
  - 공간이 필요할때, 데이터 삭제
  - 사용자가 명시적으로 의도하지 않은 삭제
- Expiration
  - 데이터에 유통기한을 둠 (ex - redis expiration)
  - 하루 지나면 필요없는 데이터 - set 1 day expiration



참고 자료 :  
[Cache에서의 Eviction, Expiration, Passivation](https://charsyam.wordpress.com/2013/06/04/%EC%9E%85-%EA%B0%9C%EB%B0%9C-cache%EC%97%90%EC%84%9C%EC%9D%98-eviction-expiration-passivation/)   
[Cache 에 대한 이론적인 정리](https://jins-dev.tistory.com/entry/Cache-%EC%97%90-%EB%8C%80%ED%95%9C-%EC%9D%B4%EB%A1%A0%EC%A0%81%EC%9D%B8-%EC%A0%95%EB%A6%AC)
