package com.commtalk.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PinnedBoard {
	
	@Column(name = "pinned_board_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
    @JoinColumn(name = "board_id")
	private Board board;

}
