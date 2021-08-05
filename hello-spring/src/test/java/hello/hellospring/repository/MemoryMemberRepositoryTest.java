package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // 메서드 호출 순서에 따라 값이 바뀌는 상황 방지용
    // 메서드 한번 호출 시마다 call back되는 메서드 - 매번 객체 초기화(이게 맞나?)
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member(); // member 객체 하나 만들기
        member.setName("spring"); // 이름은 "spring"
        //when
        repository.save(member); // 해당 member 인스턴스 save하기
        //then
        Member result = repository.findById(member.getId()).get();
            // Optional 타입
            // Optional.get()을 해줘야지 비로소 member 인스턴스의 참조변수를 나타내는 것임을 알자!!
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

}