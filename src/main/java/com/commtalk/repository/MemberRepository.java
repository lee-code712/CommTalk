package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String username);
	
}
