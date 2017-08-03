package com.lnsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.ProjectService;
import com.lnsf.service.RelationService;

@Controller
@RequestMapping("/Relation")
public class RelationController {
	
		 
	      @Autowired
		  private RelationService relationService; 
	      @Autowired
	      private ProjectService projectService;
		  
	      @ResponseBody
		  @RequestMapping("/showRelationById")  
		  public Relation showRelationById(HttpServletRequest request,Model model){ 	       
		        Relation relation = this.relationService.getRelationById(2); 
		     //   model.addAttribute("relation", relation);  
		    //    return "showRelationById";  
		        return relation;
		  } 
	    
	      @ResponseBody
		  @RequestMapping("/showAllRelations")  
		  public List<Relation> showAllRelation(HttpServletRequest request,Model model){    
			  	List<Relation> relations = this.relationService.getAllRelation(); 
		      //  model.addAttribute("relations", relations);  
		     //   return "showAllRelations"; 
			  	return relations;
		  } 
	      
	      @ResponseBody
		  @RequestMapping("/deleteRelationById")  
		  public String deleteRelationById(HttpServletRequest request,Model model){    
				int row = this.relationService.deleteRelationById(20); 
				//return "deleteRelationById";  
				return row+"";
		  } 
	      
	      @ResponseBody
		  @RequestMapping(value="/insertRelation",method=RequestMethod.POST)  
		  public String insertRelation(Relation relation){    
				int row = this.relationService.insertRelation(relation) ;
				//return "insertRelation"; 
				return row+"";
		  }
	      
	      @ResponseBody
		  @RequestMapping(value="/updateRelation",method=RequestMethod.POST)  
		  public String updateRelation(@ModelAttribute Relation relation){ 
			  	
				int row = this.relationService.updateRelationById(relation);
			//	return "updateRelation"; 
				return row+""; 
		  }
	      
	      
			
		
}
