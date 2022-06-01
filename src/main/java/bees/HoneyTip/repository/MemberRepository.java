package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
//    Member save(Member member);
//    Optional<Member> findById(Long id);
//    Optional<Member> findByEmail(String Email);
//    Optional<Member> findByName(String name);
//    Optional<Member> findByEmailAndPassword(String Email, String Password);
//    List<Member> findAll();
//    void delete(Member member);
//    Optional<Member> update(Long id, Member member);

//
//    Optional <Member> findByEmail(String Email);
//
//    Optional<Member> update(Long id, Member member);
    Optional<Member> findByEmailAndPassword(String Email, String Password);
    Optional<Member> findByEmail(String Email);
}
