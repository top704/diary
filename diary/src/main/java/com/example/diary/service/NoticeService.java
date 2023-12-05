package com.example.diary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.mapper.NoticeMapper;
import com.example.diary.vo.Notice;

@Service
@Transactional
public class NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	
	// notice list 출력
	public List<Notice> getNoticeList(int currentPage, int rowPerPage) {
	    
	    int beginRow = (currentPage - 1) * rowPerPage;
	
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("beginRow", beginRow);
	    paramMap.put("rowPerPage", rowPerPage);
	
	    return noticeMapper.selectNoticeList(paramMap);
	}
	public int getTotalPage(int rowPerPage) {
	    int totalPage = noticeMapper.totalPage();
	    return (totalPage + rowPerPage - 1) / rowPerPage;
	}
	
	// notice 상세정보
	public Notice noticeOne(int noticeNo) {
		// mapper
		Notice result = noticeMapper.selectNoticleOne(noticeNo);
		return result;
	}
	
	// notice Insert
	public int addNotice(Notice notice,RedirectAttributes red) {
		int result = noticeMapper.insertNotice(notice);
		if(result > 0) {
			red.addFlashAttribute("msg", "notice 작성 완료");
		}
		return result;
	}
	
	// notice Delete
	public int delNotice(Notice notice,RedirectAttributes red) {
		int result = noticeMapper.deleteNotice(notice);
		if(result > 0) {
			red.addFlashAttribute("msg", "notice 삭제 완료");
		}
		return result;
	}
	
	
	// memberLevel 체크
	public boolean memberLevelCk(Notice notice) {
		boolean result = true;
		System.out.println(notice.getMemberId());
		String r1 = noticeMapper.memberLevelCk(notice);
		if(null == r1) {
			System.out.println("매니저아이디 확인실패");
			result = false;
		}
		System.out.println("매니저아이디 확인완료");
		return result;
	}
    
    // notice Update
	public int upNotice(Notice notice, RedirectAttributes red) {
		int result = noticeMapper.updateNotice(notice);
		if(result > 0) {
			red.addFlashAttribute("msg", "notice 수정 완료");
		}
		return result;
	}

}
