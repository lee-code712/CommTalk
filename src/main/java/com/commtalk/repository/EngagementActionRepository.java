package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.EngagementAction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EngagementActionRepository extends JpaRepository<EngagementAction, Long> {

    @Query("SELECT ea FROM EngagementAction ea JOIN FETCH ea.member m JOIN FETCH ea.post p WHERE m.id = :memberId AND p.id = :postId")
    List<EngagementAction> findByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);
}
