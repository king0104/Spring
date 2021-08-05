package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member 인스턴스의 인덱스 정하기
        store.put(member.getId(), member); // store에 member 인스턴스 하나 넣기
        return member; // 저장한 member 인스턴스(참조변수) 리턴
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null 값이 들어가도 실행 가능하도록 함
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // ?
                .findAny(); // ?
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장소 전체 값 리턴
    }

    public void clearStore() {
        store.clear(); // 저장소 값 초기화
    }
}
