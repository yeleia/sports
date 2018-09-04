package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    /**
     * 添加留言
     * @param message
     * @return
     */
    Map<String,Object>  addMessage(Message message);

    /**
     * 审核留言
     * @param id
     * @return
     */
    Map<String,Object> checkMessage(Integer id);

    /**
     * 删除该留言
     * @param id
     * @return
     */
    Map<String,Object> deleteMessage(Integer id);

    /**
     * 查询为审核的留言
     * @return
     */
    List<Message> getNocheck();
    /**
     * 查询已经审核的留言
     *
     */

    List<Message> getCheck();
}
