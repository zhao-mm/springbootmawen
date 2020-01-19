package com.zyc.mawen.service;

import com.zyc.mawen.dto.PaginationDTO;
import com.zyc.mawen.dto.QuestionDTO;
import com.zyc.mawen.mapper.QuestionMapper;
import com.zyc.mawen.mapper.UserMapper;
import com.zyc.mawen.model.Question;
import com.zyc.mawen.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        int countPage = page;

        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        if(page<1){
            page = 1;
        }
        //分页计算
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //复制question的元素到questionDTO
            BeanUtils.copyProperties(question, questionDTO);//使用spring自带的工具，免去每个字段的get和set
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount, page, size);

        if(page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        if(page<1){
            page = 1;
        }
        //分页计算
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //复制question的元素到questionDTO
            BeanUtils.copyProperties(question, questionDTO);//使用spring自带的工具，免去每个字段的get和set
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;

    }

    public QuestionDTO getById(Integer id){
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
