package com.example.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.service.CommentService;
import com.example.diary.service.NoticeService;
import com.example.diary.vo.Comment;
import com.example.diary.vo.Notice;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("/notice/noticeHome")
	public String noticeList(HttpSession session,Model model,@RequestParam(defaultValue = "1") int currentPage) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		
        int rowPerPage = 3;

        List<Notice> noticeList = noticeService.getNoticeList(currentPage, rowPerPage);
        int lastPage = noticeService.getTotalPage(rowPerPage);

        model.addAttribute("list", noticeList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);

        return "notice/noticeHome";
    }
	
	// notice 내용 + comment list
	@GetMapping(value = "/notice/noticeOne")
	public String noticeOne(HttpSession session,Model model,@RequestParam(defaultValue = "1") int currentPage,int noticeNo,Notice notice,Comment comment) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		
		
		// noticeOne 값 가져오기
	    Notice noticeOne = noticeService.noticeOne(noticeNo);
	    
	    // comment에 noticeNo값 넣기
	    comment.setNoticeNo(noticeNo);
	    
	    // 한 페이지 당 보여줄 comment 개수
	    int rowPerPage = 5;
	    
	    // comment list 가져오기
        List<Comment> commentList = commentService.commentList(currentPage, rowPerPage, comment);
        int lastPage = commentService.getTotalPage(rowPerPage, comment);
        
        // 세션에서 아이디값 가져오기
        String memberId = (String)session.getAttribute("loginMemberId");
        
        // 매니저 확인
        notice.setMemberId(memberId);
        boolean result = noticeService.memberLevelCk(notice);
        if(result) {
        	model.addAttribute("result", "true");
        }
        // model에 comment 데이터 추가
        model.addAttribute("memberId", memberId);
        model.addAttribute("list", commentList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        
        
        System.out.println(commentList.toString());
	    // model에 데이터 추가
	    model.addAttribute("noticeOne", noticeOne);
	    
		
		return "notice/noticeOne";
	}
	
	
	
	@GetMapping("/notice/addNotice")
	public String addNotice(HttpSession session) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		
		return "notice/noticeInsert";
	}
	
	
	// notice 작성
	@PostMapping("/notice/addNotice")
	public String addNotice(HttpSession session, Notice notice, RedirectAttributes red) {
		// 세션에서 가져온 아이디 memberId 에 저장 
		notice.setMemberId((String)session.getAttribute("loginMemberId"));

		// memberLevel체크
		boolean result = noticeService.memberLevelCk(notice);
		if(result==false) {
			System.out.println("memberLevel부족");
			red.addFlashAttribute("msg", "매니저만 작성할 수 있습니다");
			return "redirect:/notice/noticeHome";
		}
		// notice 작성
	    noticeService.addNotice(notice,red);
		return "redirect:/notice/noticeHome";
	}
	
	
	// notice 삭제
	@PostMapping("/notice/deleteNotice")
	public String deleteNotice(HttpSession session, Notice notice, RedirectAttributes red) {
		// 세션에서 가져온 아이디 memberId 에 저장 
		notice.setMemberId((String)session.getAttribute("loginMemberId"));
		
		// memberLevel체크
		boolean result = noticeService.memberLevelCk(notice);
		if(result==false) {
			System.out.println("memberLevel부족");
			red.addFlashAttribute("msg", "매니저만 삭제할 수 있습니다");
			return "redirect:/notice/noticeHome";
		}
		// notice 삭제
	    noticeService.delNotice(notice,red);
		
		return "redirect:/notice/noticeHome";
	}
	
	// notice 업데이트
	@GetMapping("/notice/noticeUpdate")
	public String updateNotice(Model model, HttpSession session, Notice notice, RedirectAttributes red) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		// 세션에서 가져온 아이디 memberId 에 저장 
		notice.setMemberId((String)session.getAttribute("loginMemberId"));
		// memberLevel체크
		boolean result = noticeService.memberLevelCk(notice);
		if(result==false) {
			System.out.println("memberLevel부족");
			red.addFlashAttribute("msg", "매니저만 수정할 수 있습니다");
			return "redirect:/notice/noticeHome";
		}
		int noticeNo = notice.getNoticeNo();
		// noticeOne 값 가져오기
	    Notice noticeOne = noticeService.noticeOne(noticeNo);
	    // model에 데이터 추가
	    model.addAttribute("noticeOne", noticeOne);
	    
	    
		return "notice/noticeUpdate";
	}
	@PostMapping("/notice/noticeUpdate")
	public String updateNotice(HttpSession session, Notice notice, RedirectAttributes red) {
		// 세션에서 가져온 아이디 memberId 에 저장 
		notice.setMemberId((String)session.getAttribute("loginMemberId"));
		
		// memberLevel체크
		boolean result = noticeService.memberLevelCk(notice);
		if(result==false) {
			System.out.println("memberLevel부족");
			red.addFlashAttribute("msg", "매니저만 수정할 수 있습니다");
			return "redirect:/notice/noticeHome";
		}
		// notice 삭제
	    noticeService.upNotice(notice,red);
		
		return "redirect:/notice/noticeHome";
		
	}
	
	
	// comment 업데이트
	@GetMapping("notice/commentUpdate")
	public String commentOne(HttpSession session,Model model,Comment comment) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}
		model.addAttribute(commentService.commentOne(comment));
		return "notice/commentUpdate";
	}
	@PostMapping("notice/commentUpdate")
	public String commentUpdate(Comment comment, RedirectAttributes red) {
		// comment 업데이트 service
		commentService.CommentUpdate(comment, red);
		return "redirect:/notice/noticeHome";
	}
	
	// comment 삭제
	@GetMapping("notice/commentDelete")
	public String commentDelete(Comment comment, RedirectAttributes red) {
		// comment 삭제 service
		commentService.CommentDelete(comment, red);
		return "redirect:/notice/noticeHome";
	}
	
	// comment 작성
	@PostMapping("notice/commentInsert")
	public String commentInsert(HttpSession session, Comment comment, RedirectAttributes red) {
		// 세션에서 가져온 아이디 memberId 에 저장 
		comment.setMemberId((String)session.getAttribute("loginMemberId"));
		// comment 작성 service
		commentService.commentInsert(comment, red);
		return "redirect:/notice/noticeHome";
	}
	
}
