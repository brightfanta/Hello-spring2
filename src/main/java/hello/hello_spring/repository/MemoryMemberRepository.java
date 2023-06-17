package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository { //opt+enter 로 interface의 method 불러오기)

    private static Map<Long, Member> store = new HashMap<>();//cmd+shft+enter 행 마지막 자동완성
    private static long sequence = 0l;  // 동시성 문제 고려해야 함(여기선 단순하게 진행)
    @Override
    public Member save(Member member) { //cmd+B(메서드나 객체 구현된 코드로 이동), cmd+[(이전 소스코드로 이동)
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//return Optional
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //자바 실무 시 List를 더 많이 활용
    }

    public void clearStore() {
        store.clear();
    }
}
