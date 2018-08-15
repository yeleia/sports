package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.ContestantMapper;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.service.StudentService;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private ContestantMapper contestantMapper;
    @Override
    public Map<String, Object> addContestant(Contestant contestant) {
        if (contestantMapper.contestantIsExist(contestant.getSportid(),contestant.getStunumber())<1){
            if (contestantMapper.insert(contestant)>0){
                return ReturnUtil.ret(true,"报名成功");
            }else {
                return ReturnUtil.ret(false,"报名失败");
            }
        }else {
            return ReturnUtil.ret(false,"你已参加该体育项目");
        }

    }
}
