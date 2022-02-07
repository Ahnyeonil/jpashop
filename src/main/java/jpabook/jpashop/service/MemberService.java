package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 조회시 readOnly = true -> 성능 향상
@Transactional(readOnly = true)
/*   lombok 기능 두가지*/
// 생성자 주입을 만들어줌
/* @AllArgsConstructor */
/* final이 있는 필드만 가지고 생성자를 만들어줌 */
@RequiredArgsConstructor
public class MemberService {
/*
    @Autowired
    private MemberRepository memberRepository;
 */
/*
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/
    private final MemberRepository memberRepository;

    // 생성자 하나일 경우 생략 가능
    /* @Autowired */
/*
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    /*
    * 회원 가입
    */
    // 데이터 변경시 따로 추가
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /*
    * 회원 이름이 중복일 경우 에러 발생 로직
    */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
    * 회원 전체 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
    * 단건 조회
    */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
