package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.MessageMapper;
import org.wingstudio.sports.dao.StudentMapper;
import org.wingstudio.sports.domain.Message;
import org.wingstudio.sports.domain.Student;
import org.wingstudio.sports.service.MessageService;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.List;
import java.util.Map;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public Map<String, Object> addMessage(Message message) {
        //根据学号和姓名查询该学生是否存在
        Student student=studentMapper.getByNuNa(message.getStuname(),message.getStunum());
        if (student!=null){
            messageMapper.addMessage(message);
            return ReturnUtil.ret(true,"已留言，待审核");
        }else {
            return ReturnUtil.ret(false,"留言失败，请输入正确的个人信息");
        }
    }

    @Override
    public Map<String, Object> checkMessage(Integer id) {
        messageMapper.updateById(id);
        return ReturnUtil.ret(true,"该留言添加成功");
    }

    @Override
    public Map<String, Object> deleteMessage(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
        return ReturnUtil.ret(true,"删除成功");
    }

    @Override
    public List<Message> getNocheck() {
        return messageMapper.getNocheck();
    }

    @Override
    public List<Message> getCheck() {
        return messageMapper.getCheck();
    }
}
