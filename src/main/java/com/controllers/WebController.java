package com.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Controller
public class WebController {

	@GetMapping("/play")
	public String returnplay(@RequestParam(name = "choice", required = false) String choice, Model m) {

		int[] intArray = { 1, 2, 3};
		String result = "tie";
		int idx = new Random().nextInt(intArray.length);
		String random = String.valueOf(intArray[idx]);
		if (choice.equals("rock")&&random.equals("1"))
		{
			result="Tie";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Rock");
		}
		if (choice.equals("rock")&&random.equals("2"))
		{
			result="Computer Wins";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Paper");
		}
		if (choice.equals("rock")&&random.equals("3"))
		{
			result="You Win";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Scissor");
		}

		////
		if (choice.equals("paper")&&random.equals("1"))
		{
			result="You Win";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Rock");
		}
		if (choice.equals("paper")&&random.equals("2"))
		{
			result="Tie";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Paper");
		}
		if (choice.equals("paper")&&random.equals("3"))
		{
			result="Computer Win";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Scissor");
		}
		//
		if (choice.equals("scissor")&&random.equals("1"))
		{
			result="Computer Win";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Rock");
		}
		if (choice.equals("scissor")&&random.equals("2"))
		{
			result="You Win";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Paper");
		}
		if (choice.equals("scissor")&&random.equals("3"))
		{
			result="Tie";
			m.addAttribute("outcome", result);
			m.addAttribute("computer", "Scissor");
		}
		return "results";
	}
	@PostMapping(value = "/click")
	public String onClick() {
		return "cssPage";

	}

	@PostMapping(value = "/execute")
	public void onExecute() {
		ComboPooledDataSource cpds=new ComboPooledDataSource();
		Connection conn = null;
		try {
			cpds.setDriverClass("org.postgresql.Driver");
			cpds.setJdbcUrl("jdbc:postgresql://pxiluatdb-cluster.cluster-cyz6m40bhyw5.ap-south-1.rds.amazonaws.com:1521/postgres?currentSchema=spotpxiluatstage3");
			cpds.setUser("spotpxiluatstage3");
			cpds.setPassword("password123");
			conn=cpds.getConnection();
			Statement stmt=conn.createStatement();
			StringBuilder sb=new StringBuilder();
			sb.append("select *from px_trading_session_mstr tsm join px_me_details md on tsm.tsm_me_instance_id=md.md_me_instance_id where date(tsm_session_start_date_time)=current_date;");
			ResultSet rs=stmt.executeQuery(sb.toString());
			while(rs.next())
			{
				String tsm_is_on=rs.getString("tsm_is_on");
				String tsm_me_instance_id=rs.getString("tsm_me_instance_id");
				String tsm_session_end_date_time=rs.getString("tsm_session_end_date_time");
				String tsm_status=rs.getString("tsm_status");
				String md_me_description=rs.getString("md_me_description");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
