package com.sist.web.service;
import java.util.*;

import com.sist.web.entity.*;
import com.sist.web.vo.*;
// repository => 호출용
// service
public interface FoodService {
	public List<FoodListVO> foodListData(int start, int end);
	public FoodEntity foodDetailData(int fno);
	public int foodTotalPage();
}
