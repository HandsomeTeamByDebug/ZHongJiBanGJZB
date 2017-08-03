package com.lnsf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.UserDao;
import com.lnsf.dao.WinDao;
import com.lnsf.entity.Project;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.WinService;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月28日 上午11:56:16
 * @introduction
 */
@Service("winServiceImpl")
public class WinServiceImpl implements WinService {
	@Autowired
	WinDao winDao;
	@Autowired
	private UserDao userService;
	@Autowired
	private ProjectDao projectService;

	@Override
	public List<Win> getAllWin() {
		// 获取所有信息
		return winDao.getAllWin();
	}

	// 根据表ID查找
	@Override
	public Win getWinByWinId(Integer winId) {
		return winDao.getWinByWinId(winId);
	}

	// 根据项目ID查找中标信息
	@Override
	public Win getWinByWinByFKprojectId(Integer fkprojectId) {
		return winDao.getWinByWinByFKprojectId(fkprojectId);
	}

	// 根据用户ID查找中标信息
	@Override
	public Win getWinByWinByFKUserId(Integer userId) {
		return winDao.getWinByWinByFKUserId(userId);
	}

	/*
	 * //插入信息
	 * 
	 * @Override public Integer addWin(Win win){ //Integer
	 * userId=win.getUser().getUserId(); //Integer
	 * projectId=win.getProject().getProjectId();
	 * //System.out.println("userId:"+userId);
	 * //System.out.println("projectId:"+projectId); // return
	 * winDao.addWin(userId,projectId); return winDao.addWin(win); }
	 */
	// 根据主键ID删除信息
	@Override
	public Integer deleteWinById(Integer WINID) {
		return winDao.deleteWinById(WINID);
	}

	// 根据用户ID删除信息
	@Override
	public Integer deleteWinByUserId(Integer UserId) {
		return winDao.deleteWinByUserId(UserId);
	}

	// 根据项目ID删除信息
	@Override
	public Integer deleteWinByProjectId(Integer ProjectId) {
		return winDao.deleteWinByProjectId(ProjectId);
	}

	// 查询win表中是否有重复数据
	@Override
	public List<Win> getWinByByUserIdAndProjectId(Integer FKGETID, Integer FKPROJECTID) {
		// TODO Auto-generated method stub
		return winDao.getWinByByUserIdAndProjectId(FKGETID, FKPROJECTID);
	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年7月30日09:10:02
	 * @introduction
	 */
	// 插入信息
	@Override
	public Integer addWin(Win win) {

		Integer userId = win.getUser().getUserId();
		Integer projectId = win.getProject().getProjectId();
		// 判断有没有重复的数据
		List<Win> haswin = winDao.getWinByByUserIdAndProjectId(userId, projectId);
		if (haswin.equals("")) {
			// 考虑到有没有投标的这个人
			User user = userService.getUserById(userId);
			System.out.println(user);
			// 有没有投标的这个项目
			Project project = projectService.getProjectById(projectId);
			System.out.println(project);
			Integer userIdInProject = project.getUser().getUserId();
			System.out.println(userIdInProject);
			// isEqual为true时表明投标的项目是这个人所招标的，不能自己投自己项目
			boolean isEqual = (userId == userIdInProject);
			System.out.println(isEqual);

			if (!user.equals("") && !project.equals("") && !isEqual) {
				System.out.println("插入投标表成功");
				return winDao.addWin(win);

			} else {
				System.out.println("插入投标表失败");
				return 0;
			}
		} else {
			System.out.println("插入投标表失败");
			return 0;
		}

	}

	// 更新信息
	@Override
	public Integer updateWinByProjectId(Win win) {
		Integer userId = win.getUser().getUserId();
		Integer projectId = win.getProject().getProjectId();
		// 判断有没有重复的数据
		List<Win> haswin = winDao.getWinByByUserIdAndProjectId(userId, projectId);
		if (haswin.equals("")) {
			// 考虑到有没有中标的这个人
			User user = userService.getUserById(userId);
			System.out.println(user);
			// 有没有中标的这个项目
			Project project = projectService.getProjectById(projectId);
			System.out.println(project);
			Integer userIdInProject = project.getUser().getUserId();
			System.out.println(userIdInProject);
			// isEqual为true时表明中标的项目是这个人所中标的，不能自己投自己项目
			boolean isEqual = (userId == userIdInProject);
			System.out.println(isEqual);

			if (!user.equals("") && !project.equals("") && !isEqual) {
				System.out.println("更新中标表成功");
				return winDao.updateWinByProjectId(win);

			} else {
				System.out.println("插入中标表失败");
				return 0;
			}
		} else {
			System.out.println("插入中标表失败");
			return 0;
		}
	}

	@Override
	public int getWinCount() {
		return winDao.getWinCount();
	}
}
