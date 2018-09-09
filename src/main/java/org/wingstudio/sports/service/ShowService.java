package org.wingstudio.sports.service;

import io.swagger.models.auth.In;
import org.wingstudio.sports.VO.*;
import org.wingstudio.sports.domain.Classes;
import org.wingstudio.sports.domain.Record;
import org.wingstudio.sports.domain.TwoLevel;

import java.util.List;

public interface ShowService {

    /**
     * 根据校区查找学院
     * @param campus
     * @return
     */
    List<String> getClasses(String campus);

    /**
     * 根据性别查找体育项目,用于选项卡
     * @param sex
     * @return
     */
    List<SportVO> getSport(Integer sex);

    /**
     * 根据校区，项目id查找预赛单项前十名
     * @param campus
     * @param sportId
     * @return
     */
    List<PreSoloVO> getPreSoloVO(String campus,Integer sportId,String currentime);
    /**
     * 根据项目id查找决赛单项前十名
     * @param sportId
     * @return
     */
    List<SoloVO> getSoloVO(Integer sportId, String currentime);

    /**
     * 查询所有的学院成绩，并按总分排名
     * @param taketime
     * @return
     */
    List<Classes> getAllClasses(String taketime);

    /**
     * 根据校区和届数查找，不排名
     * @param taketime
     * @return
     */
    List<Classes> getClassesByC(String taketime);

    /**
     * 根据学号或者姓名查询,并计算名次
     * @return
     */
    List<QueryVO> getQuery(String stunu,String taketime);

    /**
     * 获取破校记录成绩
     * @param taketime
     * @return
     */
    List<RecordVO> getRecord(String taketime);

    /**
     * 获取达到二级运动员标准
     * @param taketime
     * @return
     */
    List<TwoLevelVO> getTwoLevel(String taketime);

    /**
     * 根据校区，体育项目获取集体项目
     * @param campus
     * @param taketime
     * @return
     */
    List<TeamVO> getTeamVO(String campus, Integer sportid,String taketime);


}
