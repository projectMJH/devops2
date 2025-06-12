package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Controller
/*
 * 	1. Git Action
 * 	2. 새로운 운영체제 : Docker
 * 						|
 * 					쿠버네티스 => 클러스트
 * 	------------------------ 젠킨스
 */
public class FoodController {
	@Autowired
	private FoodService fService;
	
	@GetMapping("/") 	// 파일명 / redirect => 파일에 출력할 데이터, 파일명 전송
	public String food_main(@RequestParam(name="page", defaultValue = "1") String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		List<FoodListVO> list=fService.foodListData(start, end);
		int totalpage=fService.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		// 데이터 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("main_html","main/home");
		
		return "index";
	}
	
	@GetMapping("/detail")
	public String food_detail(@RequestParam("fno") int fno, Model model)
	{
		FoodEntity vo=fService.foodDetailData(fno);
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","food/detail");
		
		return "index";
	}
}
