package bees.HoneyTip.repository;

import bees.HoneyTip.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByEmail(String Email) {
        return store.values().stream()
                .filter(member ->  member.getEmail().equals(Email))
                .findAny();
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member ->  member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String Email, String Password) {
        return store.values().stream()
                .filter(member -> member.getEmail().equals(Email))
                .filter(member -> member.getPassword().equals(Password))
                .findAny();
    }

    @Override
    public void delete(Member member) {
        store.remove(member.getId());
    }

    public void clearStore() {
        store.clear();
    }
}