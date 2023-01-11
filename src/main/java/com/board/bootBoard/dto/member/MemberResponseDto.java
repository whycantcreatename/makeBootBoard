package com.board.bootBoard.dto.member;

import java.time.LocalDateTime;

import com.board.bootBoard.entity.board.Board;
import com.board.bootBoard.entity.board.member.Member;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberResponseDto {

	private Long id;
	private String email;
	private String pwd;
	private LocalDateTime lastLoginTime;

	public MemberResponseDto(Member entity) {

		this.id = entity.getId();
		this.email = entity.getEmail();
		this.pwd = entity.getPwd();
		this.lastLoginTime = entity.getLastLoginTime();
	}
}
