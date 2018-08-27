package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.PreSoloMapper;
import org.wingstudio.sports.dao.SoloMapper;
import org.wingstudio.sports.dao.SportMapper;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.service.ScoreService;
import org.wingstudio.sports.util.CheckUtil;
import org.wingstudio.sports.util.ReturnUtil;
import java.util.Map;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private PreSoloMapper preSoloMapper;
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private SoloMapper soloMapper;

    @Override
    public Map<String, Object> addPreSoloScore(PreSolo preSolo) {
        Sport sport = sportMapper.selectByPrimaryKey(preSolo.getSportid());
        //检查输入
        if (CheckUtil.checkScore(sport.getSortrule(), preSolo.getScore(), sport.getInmax(), sport.getInmin())) {
            //检查该成绩是否已添加
            if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                preSolo.setChecked(0);
                preSoloMapper.insertSelective(preSolo);
                return ReturnUtil.ret(true, "添加成功");
            } else {
                return ReturnUtil.ret(false, "添加失败，该成绩已经添加");
            }
        } else {
            return ReturnUtil.ret(false, "添加失败，检查输入是否合格");
        }
    }

    @Override
    public Map<String, Object> updatePreSoloScore(PreSolo preSolo) {
        Sport sport = sportMapper.selectByPrimaryKey(preSolo.getSportid());
        PreSolo preSolo1 = preSoloMapper.selectByPrimaryKey(preSolo.getId());
        //检查输入格式
        if (CheckUtil.checkScore(sport.getSortrule(), preSolo.getScore(), sport.getInmax(), sport.getInmin())) {
            //检查是否只改变除sportid,Contestantid,taketime
            if (preSolo.getSportid().equals(preSolo1.getSportid()) && preSolo.getContestantid().equals(preSolo1.getContestantid()) && preSolo.getTaketime().equals(preSolo1.getTaketime())) {
                //直接修改
                preSoloMapper.updateByPrimaryKeySelective(preSolo);
                if (preSolo.getChecked()==1){
                    //该成绩已经审核，故需要重新计算成绩

                    /////////


                }
                return ReturnUtil.ret(true, "'修改成功");
            } else {
                //该记录是否存在
                if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                    //该记录不存在
                    preSoloMapper.updateByPrimaryKeySelective(preSolo);
                    if (preSolo.getChecked()==1){
                        //该成绩已经审核，故需要重新计算
                        ////

                        //
                    }
                    return ReturnUtil.ret(true, "修改成功");
                } else {
                    return ReturnUtil.ret(false, "修改失败，该记录已经存在");
                }
            }
        } else {
            return ReturnUtil.ret(false, "请检查输入格式");
        }
    }

    @Override
    public Map<String, Object> deletePreSoloScore(Integer id, Integer check) {
            if (preSoloMapper.deleteByPrimaryKey(id) > 0) {

                if (check==1){
                    //该成绩已经审核，故需要重新计算



                }
                return ReturnUtil.ret(true,"删除成功");
            } else {
               return ReturnUtil.ret(false,"删除失败");
            }

    }

    @Override
    public Map<String, Object> addSoloScore(Solo solo) {
        Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
        //检查输入
        if(CheckUtil.checkScore(sport.getSortrule(),solo.getScore(),sport.getInmax(),sport.getInmin())){
            //检查该成绩是否添加
            if(soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                solo.setChecked(0);
                soloMapper.insertSelective(solo);
                return ReturnUtil.ret(true,"添加成功");
            }else {
                return ReturnUtil.ret(false,"该成绩已经添加");
            }
        }
        return ReturnUtil.ret(false,"添加失败，请检查输入是否符合要求");
    }

    @Override
    public Map<String, Object> updateSoloScore(Solo solo) {
        Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
        Solo solo1=soloMapper.selectByPrimaryKey(solo.getId());
        //检查输入格式
        if (CheckUtil.checkScore(sport.getSortrule(),solo.getScore(),sport.getInmax(),sport.getInmin())){
            //检查是否只改变除sportid,Contestantid,taketime
            if (solo.getSportid().equals(solo1.getSportid())&&solo.getContestantid().equals(solo1.getContestantid())&&solo.getTaketime().equals(solo1.getTaketime())){
                //直接修改
                soloMapper.updateByPrimaryKeySelective(solo);
                if (solo.getChecked()==1){
                    //已经审核，重新计算
                }
                return ReturnUtil.ret(true,"修改成功");
            }else {
                //该记录是否存在
                if (soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                    soloMapper.updateByPrimaryKeySelective(solo);
                    if (solo.getChecked()==1){
                        //重新计算


                    }
                    return ReturnUtil.ret(true,"修改成功");
                }else {
                    return ReturnUtil.ret(false,"该记录已经存在");
                }
            }
        }else {
            return ReturnUtil.ret(false,"修改失败，输入不符合规则");
        }
    }

    @Override
    public Map<String, Object> deleteSoloScore(Integer id, Integer check) {
        if (soloMapper.deleteByPrimaryKey(id)>0){
            if (check==1){
                //该记录已经审核。故重新计算



            }
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }


}
