package com.board.bootBoard.dto.member;

import java.time.LocalDateTime;

import com.board.bootBoard.entity.board.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
	private Long id;
	private String email;
	private String pwd;
	private LocalDateTime lastLoginTime;
	
	public Member toEntity() {
		return Member.builder()
				.email(email)
				.pwd(pwd)
				.build();
	}
}
