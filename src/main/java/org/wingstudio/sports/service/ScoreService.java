package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.*;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    /**
     * 添加预赛单项成绩
     * @param preSolo
     * @return
     */
    Map<String,Object> addPreSoloScore(PreSolo preSolo);

    /**
     * 修改预赛单项成绩
     * @param preSolo
     * @return
     */
    Map<String,Object> updatePreSoloScore(PreSolo preSolo);

    /**
     * 删除预赛单项成绩
     * @param id
     * @return
     */
    Map<String,Object> deletePreSoloScore(Integer id);

    /**
     * 查询没有审核的预赛单项
     * @return
     */
    Map<String,Object> getPreSoloNoCheck(Integer tempPage, Integer pageCapacity, String taketime);

    /**
     * 查询已经审核的预赛单项
     * @param tempPage
     * @param pageCapacity
     * @param taketime
     * @return
     */
    Map<String,Object> getPreSoloChecked(Integer tempPage, Integer pageCapacity, String taketime);
    /**
     * 添加决赛单项成绩
     * @param solo
     * @return
     */
    Map<String,Object> addSoloScore(Solo solo);

    /**
     * 更新决赛单项成绩
     * @param solo
     * @return
     */
    Map<String,Object> updateSoloScore(Solo solo);

    /**
     * 删除决赛单项成绩
     * @param id
     * @return
     */
    Map<String,Object> deleteSoloScore(Integer id);

    /**
     * 查询决赛未审核的成绩
     * @param tempPage
     * @param pageCapacity
     * @param taketime
     * @return
     */
    Map<String,Object> getSoloScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime);

    /**
     * 查询决赛已经审核过的成绩
     * @param tempPage
     * @param pageCapacity
     * @param taketime
     * @return
     */
    Map<String,Object> getSoloScoreCheck(Integer tempPage, Integer pageCapacity, String taketime);
    /**
     * 添加集体项目成绩
     * @param teamScore
     * @return
     */
    Map<String,Object> addTeamScore(TeamScore teamScore);

    /**
     * 修改集体项目的成绩
     * @param teamScore
     * @return
     */
    Map<String,Object> updateTeamScore(TeamScore teamScore);



    /**
     * 删除集项目的成绩
     * @param id
     * @return
     */
    Map<String,Object> deleteTeamScore(Integer id);

    /**
     * 查询集体项目未审核成绩
     * @param tempPage
     * @param pageCapacity
     * @param taketime
     * @return
     */
    Map<String,Object> getTeamScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime);

    /**
     * 查询集体项目已经审核的
     * @param tempPage
     * @param pageCapacity
     * @param taketime
     * @return
     */
    Map<String,Object> getTeamScoreCheck(Integer tempPage, Integer pageCapacity, String taketime);

    /**
     * 审核预赛成绩，添加时并计算
     * @param id
     * @return
     */
    Map<String,Object> checkPreSolo(Integer id);

    /**
     * 审核决赛成绩，添加时并计算
     * @param id
     * @return
     */
    Map<String,Object> checkSolo(Integer id);

    /**
     * 审核集体项目成绩
     * @param id
     * @return
     */
    Map<String,Object> checkTeamScore(Integer id);

    /**
     * 计算单项总分
     */
    Map<String,Object> countAll(String taketime);

}
