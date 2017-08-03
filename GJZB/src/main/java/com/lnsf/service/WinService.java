package com.lnsf.service;

import java.util.List;

import com.lnsf.entity.Win;
import org.apache.ibatis.annotations.Param;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月28日 上午11:55:40
 * @introduction
 */
public interface WinService {
	// 查询全部用户信息
	List<Win> getAllWin();

	// 根据表ID查找
	Win getWinByWinId(Integer winId);

	// 根据项目ID查找中标信息
	Win getWinByWinByFKprojectId(Integer fkprojectId);

	// 根据用户ID查找中标信息
	Win getWinByWinByFKUserId(Integer userId);

	// 插入信息
	Integer addWin(Win win);

	// Integer addWin(Integer userId,Integer projectId);
	// 根据主键ID删除信息
	Integer deleteWinById(Integer WINID);

	// 根据用户ID删除信息
	Integer deleteWinByUserId(Integer UserId);

	// 根据项目ID删除信息
	Integer deleteWinByProjectId(Integer ProjectId);

	// 修改根据项目ID信息
	Integer updateWinByProjectId(Win win);

	// 查询win表中是否有重复的数据
	List<Win> getWinByByUserIdAndProjectId(@Param("FKGETID") Integer FKGETID,
			@Param("FKPROJECTID") Integer FKPROJECTID);

	/**
	 * 用于首页的报表，展示中标的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getWinCount();
}
