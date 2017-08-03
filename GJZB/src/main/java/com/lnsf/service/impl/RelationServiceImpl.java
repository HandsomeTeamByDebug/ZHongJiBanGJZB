package com.lnsf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.UserDao;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.service.RelationService;

@Service("relationService")
public class RelationServiceImpl implements RelationService {

	@Autowired
	private RelationDao relationDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	
	//根据用户ID查询投标表信息
	@Override
	public List<Relation> getRelationByUserId(Integer FKPUTID){
		return relationDao.getRelationByUserId(FKPUTID);
	}
	
	@Override
	public List<Relation> getAllRelation() {
		// TODO Auto-generated method stub
		return relationDao.getAllRelation();
	}

	@Override
	public Relation getRelationById(Integer relationId) {
		// TODO Auto-generated method stub
		return relationDao.getRelationById(relationId);
	}

	@Override
	public int deleteRelationById(Integer relationId) {
		// TODO Auto-generated method stub
		return relationDao.deleteRelationById(relationId);
	}

	/**
	 * 劳伟玲
	 * 更新这个投标表时，要考虑到有没有招标的这个人，
	 * 有没有投标的这个项目
	 * @return 0 表明更新失败
	 * 		   1 表明更新成功
	 * 
	 */
	/*@Override
	public int updateRelationById(Relation relation) {
		// TODO Auto-generated method stub
		return relationDao.updateRelationById(relation);
	}*/
	@Override
	public int updateRelationById(Relation relation) {
		
		Integer userId = relation.getUser().getUserId();
		
		Integer projectId = relation.getProject().getProjectId();
		List<Relation> hasRelation = relationDao.getRelationByuserIdAndProjectId(userId, projectId);
		if(hasRelation.equals("")){	
				//考虑到有没有投标的这个人
				User user=userDao.getUserById(userId);
				System.out.println(user);
				//有没有投标的这个项目
				Project project = projectDao.getProjectById(projectId);
				System.out.println(project);
				Integer userIdInProject = project.getUser().getUserId();
				System.out.println(userIdInProject);
				//isEqual为true时表明投标的项目是这个人所招标的，不能自己投自己项目
				boolean isEqual = (userId == userIdInProject ); 
				System.out.println(isEqual);
				
				if(!user.equals("")&&!project.equals("")&&!isEqual){
					System.out.println("更新投标表成功");
					return relationDao.updateRelationById(relation);
					
				}else{
					System.out.println("更新投标表失败");
					return 0;
				}
		}else{
			System.out.println("插入投标表失败");
			return 0;
		}

		
	
		
	}
	/*
	@Override
	public int insertRelation(Relation relation) {
		// TODO Auto-generated method stub
		return relationDao.insertRelation(relation);
	}*/
	
	/**
	 * 劳伟玲
	 * 
	 */
	@Override
	public int insertRelation(Relation relation) {
		
		Integer userId = relation.getUser().getUserId();
		Integer projectId = relation.getProject().getProjectId();
		//判断有没有重复的数据
		List<Relation> hasRelation = relationDao.getRelationByuserIdAndProjectId(userId, projectId);
		if(hasRelation.equals("")){	
				//考虑到有没有投标的这个人
				User user=userDao.getUserById(userId);
				System.out.println(user);
				//有没有投标的这个项目
				Project project = projectDao.getProjectById(projectId);
				System.out.println(project);
				Integer userIdInProject = project.getUser().getUserId();
				System.out.println(userIdInProject);
				//isEqual为true时表明投标的项目是这个人所招标的，不能自己投自己项目
				boolean isEqual = (userId == userIdInProject ); 
				System.out.println(isEqual);
			
				if(!user.equals("")&&!project.equals("")&&!isEqual){
					System.out.println("插入投标表成功");
					return relationDao.insertRelation(relation);
					
				}else{
					System.out.println("插入投标表失败");
					return 0;
				}	
	}else{
		System.out.println("插入投标表失败");
		return 0;
	}

}
	
	@Override
	public List<Relation> getRelationByuserIdAndProjectId(Integer FKPUTID, Integer FKPROJECTID) {
		// TODO Auto-generated method stub
		return relationDao.getRelationByuserIdAndProjectId(FKPUTID, FKPROJECTID);
	}
	
	/**
	* @author 黄卉
	* @version 创建时间：2017年7月27日20:20:58
	* @introduction  删除投标表，根据用户删除ID
	*/
	@Override
	public int deleteRelationByUserId(Integer FKPUTID){
		return relationDao.deleteRelationByUserId(FKPUTID);
	}
	/**
	* @author 黄卉
	* @version 创建时间：2017年7月27日20:20:58
	* @introduction  删除投标表，根据项目删除ID
	*/
	@Override
	public int deleteRelationByProjectId(Integer FKPROJECTID){
		return relationDao.deleteRelationByProjectId(FKPROJECTID);
	}

	@Override
	public int getRelationCount() {
		return relationDao.getRelationCount();
	}

	@Override
	public List<Relation> getAllRelationsByUserId(Integer userId) {
		return relationDao.getRelationByUserId(userId);
	}

	@Override
	public List<Relation> getAllProjectsByUserIdPage(Page<Project> project_page, Integer FKPUTID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCountTotalNum(Integer userId) {
		return relationDao.getTotalNum(userId);
	}

}
