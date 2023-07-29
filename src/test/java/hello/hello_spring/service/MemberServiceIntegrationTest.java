package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //Test 시 db 사용 후 데이터  roll back(반복 테스트 가능하게 함) - 테스트에 데이터 반영되지 않음
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() {//test는 과감하게 한글로 바꾸자, fn+shft+F6: rename locally
        //given: 이런 상황이 주어졌을 때
        Member member = new Member();
        member.setName("spring1234567890");

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


    }

}