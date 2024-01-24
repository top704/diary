package com.example.diary.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.service.CalendarService;
import com.example.diary.service.MemberService;
import com.example.diary.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	private CalendarService calendarService;
	
	// 로그인
	@GetMapping("/member/login")
	public String login(HttpSession session, Model model) {
		// 로그인 체크시 
		if(session.getAttribute("loginMemberId")!=null) {
			return "redirect:/member/home";
		}
		return "member/loginMember";
	}
	
	@PostMapping("/member/login")
	public String login(HttpSession session,Member paramMember, RedirectAttributes red) {
		Member loginMember = memberService.login(paramMember);
		if(loginMember == null) {
			System.out.println("로그인 실패");
			// 로그인 실패 등등 메세지를 보낼때 RedirectAttributes red 를 위에 추가하고 red.addFlashAttribute 사용함
			red.addFlashAttribute("msg", "아이디 또는 비밀번호를 확인하세요");	 
			return "redirect:/member/login";
		}
		System.out.println(loginMember.getMemberId()+" <<<< 세션에 저장될 ID");
		// 세션에 id값 저장
		session.setAttribute("loginMemberId", loginMember.getMemberId());
		return "redirect:/member/home";
	}
	
	// 회원가입
	@GetMapping("/member/addMember")
	public String addMember(HttpSession session) {
		if(session.getAttribute("loginMemberId")!=null) {
			return "redirect:/member/home";
		}
		return "/member/addMember";
	}
	@PostMapping("/member/addMember")
	public String addMember(Member member) {
		int addMember = memberService.addMember(member);
		if(addMember == 0) {
			System.out.println("회원가입 실패");
			return "redirect:/member/login";
		}
		
		return "redirect:/member/login";
	}
	@PostMapping("/member/idCk")
	@ResponseBody
	public int idCk(String memberId) {
		int result = memberService.idCk(memberId);
		System.out.println(result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 로그아웃 
	@GetMapping("/member/logout")
	public String logout(Model model,HttpSession session,RedirectAttributes red) {
		red.addFlashAttribute("msg", model.getAttribute("msg"));
		session.invalidate();

		return "redirect:/member/login";
	}
	
	// 회원정보수정화면
	@GetMapping("/member/modifyMember")
	public String modify(HttpSession session) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		return "member/modifyMember";
	}
	// 수정작업
	@PostMapping("/member/modifyMember")
	public String modify(HttpSession session, Member member,RedirectAttributes red) {
		member.setMemberId((String)session.getAttribute("loginMemberId"));
		String msg = memberService.updateMember(member,red);
		red.addFlashAttribute("msg", msg);
		return "redirect:/member/home";
	}
	
	// 회원정보삭제화면
	@GetMapping("/member/removeMember")
	public String delete(HttpSession session) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		return "member/removeMember";
	}
	// 수정작업
	@PostMapping("/member/removeMember")
	public String delete(HttpSession session, Member member,RedirectAttributes red) {
		member.setMemberId((String)session.getAttribute("loginMemberId"));
		String msg = memberService.deleteMember(member,red);
		if(msg.equals("비밀번호를 확인하세요")) {
			red.addFlashAttribute("msg", msg);
			return "redirect:/member/home";
		}
		red.addFlashAttribute("msg", msg);
		return "redirect:/member/logout";
	}
	
	
}
