package com.example.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.service.ScheduleService;
import com.example.diary.vo.Schedule;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	
	// 스케줄 일별 리스트
	@GetMapping("/schedule/scheduleListByDay")
	public String selectScheduleListByDay(HttpSession session,Model model,String memberId, int targetYear, int targetMonth, int toDay){
		
		List<Schedule> listDay = scheduleService.getScheduleListByDay((String)session.getAttribute("loginMemberId"), targetYear, targetMonth, toDay);
		model.addAttribute("list",listDay);
		
		return "schedule/scheduleListByDay";
	}
	
	// 스케줄 업데이트
	@GetMapping("/schedule/scheduleUpdate")
	public String scheduleUpdate(Model model,Schedule schedule, RedirectAttributes red) {
		Schedule getSchedule = scheduleService.getSchedule(schedule);
		model.addAttribute("schedule",getSchedule);
		
		return "schedule/scheduleUpdate";
	}
	@PostMapping("schedule/scheduleUpdate")
	public String scheduleUpdate(HttpSession session,Schedule schedule, RedirectAttributes red) {
		scheduleService.scheduleUpdate(schedule,red);
		return "redirect:/member/home";
	}
	
	// 스케줄 삭제
	@GetMapping("schedule/scheduleDelete")
	public String scheduleDelete(HttpSession session,Schedule schedule, RedirectAttributes red) {
		scheduleService.scheduleDelete(schedule,red);
		return "redirect:/member/home";
	}
	// 스케줄 등록
	@PostMapping("schedule/scheduleInsert")
	public String scheduleInsert(HttpSession session,Schedule schedule, RedirectAttributes red) {
		schedule.setMemberId((String)session.getAttribute("loginMemberId"));
		System.out.println(schedule.getMemberId());
		scheduleService.scheduleInsert(schedule,red);
		return "redirect:/member/home";
	}
	
}
