package hello.hello_spring.service;

import hello.hello_spring.domain.Member;

import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //Dependency Injection!
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();//부분 테스트 시마다 데이터 정리되어 충돌되지 않도록, 독립성 확보(순서, 상호 의존성 없앤다)
    }

    @Test
    void 회원가입() {//test는 과감하게 한글로 바꾸자, fn+shft+F6: rename locally
        //given: 이런 상황이 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when: 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then: 그러면 이 결과가 나와야 함
        Member findMember = memberService.findOne(saveId).get();//opt+cmd+V
//        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); opt+enter: static import
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/* opt+cmd+/
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}