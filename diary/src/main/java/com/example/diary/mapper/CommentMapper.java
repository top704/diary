package com.example.diary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.diary.vo.Comment;

@Mapper
public interface CommentMapper {
	// comment 목록
	List<Comment> selectCommentList(Map<String, Object> paramMap);
	int totalPage(Comment comment);
	// comment 수정
	Comment selectComment(Comment comment);
	int updateComment(Comment comment);
	int deleteComment(Comment comment);
	int insertComment(Comment comment);
	
}
