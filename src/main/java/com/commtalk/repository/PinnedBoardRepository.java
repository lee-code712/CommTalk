package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.commtalk.model.PinnedBoard;

@Repository
public interface PinnedBoardRepository extends JpaRepository<PinnedBoard, Long> {

	@Query("SELECT DISTINCT pb FROM PinnedBoard pb LEFT JOIN FETCH pb.member m JOIN FETCH pb.board b")
	List<PinnedBoard> findByMemberId(Long memberId);
	
}
