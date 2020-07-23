package com.korea.plate.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateDTO {

	private String year = "";
	private String month = "";
	private String date = "";
	private String value = "";

	public DateDTO() {
	}
	
	public DateDTO(String year, String month, String date, String value) {
		if ((month != null && month != "") && (date != null && date != "")) {
			this.year = year;
			this.month = month;
			this.date = date;
			this.value = value;
		}
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, Integer> this_month(DateDTO dDTO) {
		
		// 이번달 정보 Map에 담아
		Map<String, Integer> this_month_data = new HashMap<String, Integer>();

		// calendar 객체에 dDTO의 년/월의 1일 세팅
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dDTO.getYear()), Integer.parseInt(dDTO.getMonth()), 1);
		
		// dDTO의 '월'의 1일 요일, 말일, 요일을 구한다
		int startDay = cal.getMinimum(java.util.Calendar.DATE); // DATE: 1~31일 -> 의 getMinimu은 1
		int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); // DAY_OF_MONTH: 0~11월 -> getActualMaximum은 28, 30, 31 중 1개 
		int start = cal.get(java.util.Calendar.DAY_OF_WEEK); // 1(일) ~ 7(토)
		
		// calendar 객체에 오늘 날짜 세팅
		Calendar todayCal = Calendar.getInstance();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");

		// '오늘'에 해당하는 '년도', '월'을 simpleDateFormat을 이용해 구한다
		int today_year = Integer.parseInt(ysdf.format(todayCal.getTime())); // getTime()은 시간을 밀리세컨드로 변환해서 숫자 데이터로 반환한다
		int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));

		int next_year = Integer.parseInt(dDTO.getYear());
		int next_month = Integer.parseInt(dDTO.getMonth()) + 1;
		
		int today = -1;
		// '올해' = '찾는 해', '이번달' = '찾는 달' == 오늘
		if (today_year == next_year && today_month == next_month) {
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			today = Integer.parseInt(dsdf.format(todayCal.getTime()));
		}
		
		next_month = next_month-1; 
		
		// 페이지 상단의 > 구현
		Map<String, Integer> before_after_calendar = before_after_calendar(next_year,next_month);
		
		this_month_data.put("start", start); // 1일에 해당하는 요일
		this_month_data.put("startDay", startDay); // 1
		this_month_data.put("endDay", endDay); // 말일
		this_month_data.put("today", today); // 오늘날짜 dd
		this_month_data.put("search_year", next_year);
		this_month_data.put("search_month", next_month+1);
		this_month_data.put("after_year", before_after_calendar.get("after_year")); // 다음년도
		this_month_data.put("after_month", before_after_calendar.get("after_month")); // 다음달
		return this_month_data;
	}
	
	// 다음달 다음년도
	private Map<String, Integer> before_after_calendar(int search_year, int search_month){
		Map<String, Integer> before_after_data = new HashMap<String, Integer>();
		int after_year = search_year;
		int after_month = search_month+1; // 다음 달
		
		if(after_month>11){ // 다음달이 11보다 크면 내년이야 (11이 12월)
			after_month=0;
			after_year=search_year+1;
		}
		
		before_after_data.put("after_year", after_year);
		before_after_data.put("after_month", after_month);
		
		return before_after_data;
	}

	
}
