package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.ContestantMapper;
import org.wingstudio.sports.dao.StudentMapper;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.service.StudentService;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private ContestantMapper contestantMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, Object> addContestant(Contestant contestant) {
        if (contestantMapper.contestantIsExist(contestant.getSportid(),contestant.getStunumber(),contestant.getCurrentime())<1){
            if (contestantMapper.insert(contestant)>0){
                return ReturnUtil.ret(true,"报名成功");
            }else {
                return ReturnUtil.ret(false,"报名失败");
            }
        }else {
            return ReturnUtil.ret(false,"你已参加该体育项目");
        }

    }

    @Override
    public Map<String, Object> updateContestant(Contestant contestant) {
        Contestant contestant1=contestantMapper.selectByPrimaryKey(contestant.getId());
        if (contestant.getStunumber().equals(contestant1.getStunumber())&&contestant.getSportid().equals(contestant1.getSportid())){
            if (contestantMapper.updateByPrimaryKeySelective(contestant)>0){
                return ReturnUtil.ret(true,"修改成功");
            }else {
                return ReturnUtil.ret(false,"修改失败");
            }
        }else {
            if(contestantMapper.contestantIsExist(contestant.getSportid(),contestant.getStunumber(),contestant.getCurrentime())<1){
                if (contestantMapper.updateByPrimaryKeySelective(contestant)>0){
                    return ReturnUtil.ret(true,"修改成功");
                }else {
                    return ReturnUtil.ret(false,"修改失败");

                }
            }else {
                return ReturnUtil.ret(false,"你已参加该体育项目");
            }
        }
    }

    @Override
    public Map<String, Object> deleteContestant(Integer id) {
        if (contestantMapper.deleteByPrimaryKey(id) > 0) {
            return ReturnUtil.ret(true, "删除成功");
        } else {
            return ReturnUtil.ret(false, "删除失败");
        }
    }

    @Override
    public Map<String,Object> getContantList(Integer tempPage, Integer pageCapacity,String currentime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("contestant",contestantMapper.getContestList(tempPage*pageCapacity,pageCapacity,currentime));
        resultMap.put("count",contestantMapper.count(currentime));
        return resultMap;
    }

    @Override
    public List<Contestant> getContestantByNum(String stuNum,String currentime) {
        return contestantMapper.getContestantByNum(stuNum,currentime);
    }

    @Override
    public Map<String, Object> getCCP() {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("campus",studentMapper.getCampus());
        resultMap.put("classes",studentMapper.getClasses());
        resultMap.put("profession",studentMapper.getProfession());
        return resultMap;
    }

}
