package com.example.diary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.diary.service.CalendarService;
import com.example.diary.service.MemberService;
import com.example.diary.service.ScheduleService;
import com.example.diary.vo.Schedule;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private CalendarService calendarService;
	@Autowired
	private ScheduleService scheduleService;
	// 홈으로 보내기 매핑
		@GetMapping("/member/home")
	public String memberHome(HttpSession session,Model model, 
			@RequestParam(required = false) Integer targetYear, 
			@RequestParam(required = false) Integer targetMonth) {
		if(session.getAttribute("loginMemberId")==null) {
			return "redirect:/member/login";
		}


		
		Map<String, Object> calendarMap = calendarService.getCalendar(targetYear, targetMonth);
		model.addAttribute("calendarMap", calendarMap);
		
		// 스케줄 리스트 뽑을 때 month 데이터 제대로 넣어주기
		int goMonth = 0;
		int targetMonth1 = (int)(calendarMap.get("targetMonth"));
		int targetYear1 = (int)(calendarMap.get("targetYear"));
		if(targetMonth1 == 12) {
			goMonth = 1;
		}else {
			goMonth = targetMonth1+1;
		}
		System.out.println(goMonth);
		List<Map<String, Object>> list = scheduleService.getScheduleListByMonth((String)session.getAttribute("loginMemberId"), targetYear1, goMonth);
		
		model.addAttribute("list",list);
		
		return "member/home";
	}
		
}
