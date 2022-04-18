package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String Email);
    Optional<Member> findByName(String name);
    Optional<Member> findByEmailAndPassword(String Email, String Password);
    List<Member> findAll();
    void delete(Member member);
}
