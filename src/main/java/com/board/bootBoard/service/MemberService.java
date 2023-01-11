package com.board.bootBoard.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	import com.board.bootBoard.dto.member.MemberRequestDto;
import com.board.bootBoard.dto.member.MemberResponseDto;
import com.board.bootBoard.entity.board.member.Member;
import com.board.bootBoard.entity.board.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		Member member = memberRepository.findByEmail(email);
		if (member == null) throw new UsernameNotFoundException("Not Found account."); 
		return member;
	}
	
	@Transactional
	public Long save(MemberRequestDto memberSaveDto) {
		return memberRepository.save(memberSaveDto.toEntity()).getId();
	}
	
	public MemberResponseDto findById(Long id) {
		return new MemberResponseDto(memberRepository.findById(id).get());
	}
	
	public void deleteById(Long id) {
		memberRepository.deleteById(id);
	}
}