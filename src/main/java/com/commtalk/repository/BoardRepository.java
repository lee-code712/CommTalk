package com.commtalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commtalk.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByIdIn(List<Long> boardIds);
	
}
