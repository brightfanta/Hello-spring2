package hello.hello_spring;

import hello.hello_spring.repository.*;

import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); //cmd+P
    }

//    @Bean
//    public TimeTraceAop2 timeTraceAop() {
//        return new TimeTraceAop2();
//    }

/*    @Bean
    public MemberRepository memberRepository() {

        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/
}
