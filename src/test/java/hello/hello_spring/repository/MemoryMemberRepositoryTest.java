package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {


    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();//부분 테스트 시마다 데이터 정리되어 충돌되지 않도록, 독립성 확보(순서, 상호 의존성 없앤다)
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, null); //cmd+p 매개변수 확인
//        Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result); // opt+enter(static import 실행(코드 상단에 static import 생성)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("sungyun1");
        repository.save(member1);

        Member member2 = new Member(); //fn+shft+F6(rename locally)
        member2.setName("sungyun2");
        repository.save(member2);

//        Optional<Member> result = repository.findByName("sungyun1").get(); // opt+cmd+v
        Member result = repository.findByName("sungyun1").get(); // opt+cmd+v

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("sungyun1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sungyun2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
