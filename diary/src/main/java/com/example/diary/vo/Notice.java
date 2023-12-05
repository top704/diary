package com.example.diary.vo;

import lombok.Data;

@Data
public class Notice {
	// FK : member.member_id
	
	private int noticeNo;
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
	private String noticePw;
	private String createdate;
	
	
	
}