package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commtalk.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByNickname(String nickname);
	
}
