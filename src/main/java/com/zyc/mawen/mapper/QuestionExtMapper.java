package com.zyc.mawen.mapper;

import com.zyc.mawen.model.Question;
import com.zyc.mawen.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
}