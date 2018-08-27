package org.wingstudio.sports.service;

import io.swagger.models.auth.In;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;

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
     * 删除单项成绩
     * @param id
     * @return
     */
    Map<String,Object> deletePreSoloScore(Integer id, Integer check);

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
     * @param check
     * @return
     */
    Map<String,Object> deleteSoloScore(Integer id,Integer check);
}
