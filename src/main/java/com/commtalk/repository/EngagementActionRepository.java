package com.commtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commtalk.model.EngagementAction;
import com.commtalk.model.EngagementAction.ActionType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EngagementActionRepository extends JpaRepository<EngagementAction, Long> {

    @Query("SELECT ea FROM EngagementAction ea JOIN FETCH ea.member m WHERE m.id = :memberId AND ea.refId = :refId")
    List<EngagementAction> findByMemberIdAndRefId(@Param("memberId") Long memberId, @Param("refId") Long refId);

    @Query("SELECT ea FROM EngagementAction ea JOIN FETCH ea.member m WHERE m.id = :memberId AND ea.action = :action")
    List<EngagementAction> findByMemberIdAndActionType(@Param("memberId") Long memberId, @Param("action") ActionType action);

    @Query("SELECT ea FROM EngagementAction ea JOIN FETCH ea.member m WHERE m.id = :memberId AND ea.refId = :refId AND ea.action = :action")
    EngagementAction findByMemberIdAndRefIdAndAction(@Param("memberId") Long memberId, @Param("refId") Long refId,
                                                         @Param("action") ActionType action);
}
