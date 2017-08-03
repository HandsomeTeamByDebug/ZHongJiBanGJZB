package com.lnsf.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.RelationService;
import com.lnsf.service.UserService;
import com.lnsf.service.WinService;
import com.lnsf.service.impl.RelationServiceImpl;
import com.lnsf.service.impl.WinServiceImpl;
import com.lnsf.util.RC4Encrypt;

@Controller
@RequestMapping("/Win")
public class WinController {
	
		 
	      @Autowired
		  private WinService winService; 
		  
		  @ResponseBody
		  @RequestMapping("/showWinById")  
		  public Win showWinById(HttpServletRequest request,Model model){ 	       
		        Win win = this.winService.getWinByWinId(2); 
		      //  model.addAttribute("win", win);  
		      //  return "showWinById";  
		        return win;
		  } 
	    
		  @ResponseBody
		  @RequestMapping("/showAllWins")  
		  public List<Win> showAllRelation(HttpServletRequest request,Model model){    
			  	List<Win> wins = this.winService.getAllWin(); 
		      //  model.addAttribute("wins", wins);  
		     //   return "showAllWins"; 
			  	return wins;
		  } 
		  @ResponseBody
		  @RequestMapping("/deleteWinById")  
		  public int deleteWinById(HttpServletRequest request,Model model){    
				int row = this.winService.deleteWinById(2);
			//	return "deleteWinById"; 
				return row;
		  } 
		  @ResponseBody
		  @RequestMapping(value="/insertWin",method=RequestMethod.POST)  
		  public int insertRelation(Win win){    
				int row = this.winService.addWin(win) ;
				//return "insertwin";  
				return row;
		  }
		  @ResponseBody
		  @RequestMapping(value="/updateWin",method=RequestMethod.POST)  
		  public int updateRelation(@ModelAttribute Win win){ 
			  	
				int row = this.winService.updateWinByProjectId(win);
				//return "updateRelation";  
				return row;
		  }
		   
		
}
