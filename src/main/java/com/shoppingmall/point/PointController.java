package com.shoppingmall.point;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.point.bo.PointBO;
import com.shoppingmall.point.model.Point;

@RequestMapping("/point")
@Controller
public class PointController {

	@Autowired
	private PointBO pointBO;
	
	@RequestMapping("/point_view")
	public String pointView(Model model, HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		
		List<Point> pointList = pointBO.getPointListByUserId(userId);

		model.addAttribute("pointList", pointList);
		model.addAttribute("view", "point/pointView");
		return "template/layout";
	}
}
