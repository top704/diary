// service는 mapper를 호출한다
package com.example.diary.service;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.mapper.MemberMapper;
import com.example.diary.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	public Member login(Member paramMember) {
		Member resultMember = memberMapper.login(paramMember);
		return resultMember;
	}
	
	// 회원가입
	public int addMember(Member member) {
		int resultMember = memberMapper.insertMember(member);
		return resultMember;
	}
	
	// 회원정보수정
	public String updateMember(Member member, RedirectAttributes red) {
		int result = memberMapper.updateMember(member);
		String msg = null;
		if(result>0) {
			msg = "회원정보 수정완료";
		}else {
			msg = "회원정보 수정실패(비밀번호를 확인하세요)";
		}
		
		return msg;
	}
	// 회원탈퇴
	public String deleteMember(Member member, RedirectAttributes red) {
		int result = memberMapper.deleteMember(member);
		String msg = null;
		if(result>0) {
			msg = "회원탈퇴 완료";
		}else {
			msg = "비밀번호를 확인하세요";
		}
		
		return msg;
	}
}
