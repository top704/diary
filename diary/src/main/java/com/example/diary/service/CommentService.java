package com.example.diary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.mapper.CommentMapper;
import com.example.diary.vo.Comment;
import com.example.diary.vo.Notice;

@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	// 해당 notice의 comment list 출력
	public List<Comment> commentList(int currentPage, int rowPerPage, Comment comment) {
		
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("noticeNo", comment.getNoticeNo());
		
		return commentMapper.selectCommentList(paramMap);
	}
	public int getTotalPage(int rowPerPage,Comment comment) {
        int totalPage = commentMapper.totalPage(comment);
        return (totalPage + rowPerPage - 1) / rowPerPage;
    }
	
	// commentOne
	public Comment commentOne(Comment comment) {
		Comment commentone = commentMapper.selectComment(comment);
		return commentone;
	}
	
	// commentUpdate
	public int CommentUpdate(Comment comment, RedirectAttributes red) {
		if(comment.getIsSecret() == null) {
			comment.setIsSecret("N");
		}
		int result = commentMapper.updateComment(comment);
		if(result > 0) {
			red.addFlashAttribute("msg", "comment 수정 완료");
		}
		return result;
	}
	// commentDelete
	public int CommentDelete(Comment comment, RedirectAttributes red) {
		int result = commentMapper.deleteComment(comment);
		if(result > 0) {
			red.addFlashAttribute("msg", "comment 삭제 완료");
		}
		return result;
	}
	
	// commentInsert
	public int commentInsert(Comment comment, RedirectAttributes red) {
		if(comment.getIsSecret() == null) {
			comment.setIsSecret("N");
		}
		int result = commentMapper.insertComment(comment);
		if(result > 0) {
			red.addFlashAttribute("msg", "comment 작성 완료");
		}
		return result;
	}
	
	
}
