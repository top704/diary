package com.example.diary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.mapper.ScheduleMapper;
import com.example.diary.vo.Schedule;

@Service
@Transactional
public class ScheduleService {
	@Autowired
	private ScheduleMapper scheduleMapper;
	// 스케줄 월별 리스트
	public List<Map<String, Object>> getScheduleListByMonth(String memberId, int year, int month){
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId);
		paramMap.put("year", year);
		paramMap.put("month", month);
		List<Map<String, Object>> list = scheduleMapper.selectScheduleListByMonth(paramMap);
		
		return list;
	}
	// 스케줄 일별 리스스
	public List<Schedule> getScheduleListByDay(String memberId, int year, int month, int day){
		// Schedule.vo 선언
		Schedule schedule = new Schedule();
		// 스케줄 리스트 뽑을 때 month 데이터 제대로 넣어주기
		
		
		// 받은 날짜를 ScheduelDate로 합성
		String ScheduelDate = year+"-"+month+"-"+day; 
		schedule.setScheduleDate(ScheduelDate);
		schedule.setMemberId(memberId);
		
		List<Schedule> list = scheduleMapper.selectScheduleListByDay(schedule);
		System.out.println(list.toString());
		return list;
	}
	
	// 스케줄 수정
	public Schedule getSchedule(Schedule Schedule) {
		Schedule schedule = scheduleMapper.getSchedule(Schedule);
		return schedule;
	}
	public int scheduleUpdate(Schedule schedule, RedirectAttributes red) {
		
		int result = scheduleMapper.scheduleUpdate(schedule);
		if(result > 0) {
			red.addFlashAttribute("msg", "스케줄 수정완료");
		}
		return result;
	}
	// 스케줄 삭제
	public int scheduleDelete(Schedule schedule, RedirectAttributes red) {
		
		int result = scheduleMapper.scheduleDelete(schedule);
		if(result > 0) {
			red.addFlashAttribute("msg", "스케줄 삭제완료");
		}
		return result; 
	}
	// 스케줄 등록
	public int scheduleInsert(Schedule schedule, RedirectAttributes red) {
		
		int result = scheduleMapper.scheduleInsert(schedule);
		if(result > 0) {
			red.addFlashAttribute("msg", "스케줄 등록완료");
		}
		return result; 
	}
	
	
}
