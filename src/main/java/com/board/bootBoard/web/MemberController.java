package com.board.bootBoard.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.bootBoard.dto.member.MemberRequestDto;
import com.board.bootBoard.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	
	private final MemberService memberService;
	
	@GetMapping("/login")
	public String getLoginPage(Model model,
			@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "exception", required = false) String exception) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/member/login";
	}
	
	@GetMapping("/join")
	public String getBoardWritePage(Model model, MemberRequestDto memberRequestDto) {
		return "/member/join";
	}
	
	@PostMapping("/join/action")
	public String create(Model model,  MemberRequestDto memberRequestDto) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		memberRequestDto.setPwd(passwordEncoder.encode(memberRequestDto.getPwd()));
		try {
			Long result = memberService.save(memberRequestDto);
			
			if (result < 0) {
				throw new Exception("#Exception memberJoinAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "/member/login";
	}
}