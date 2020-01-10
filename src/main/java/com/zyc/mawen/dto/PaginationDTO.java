package com.zyc.mawen.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//首页
    private boolean showNext;//下一页
    private boolean showEndPage;//尾页
    private Integer page;//当前页数
    private List<Integer> pages = new ArrayList<>();//当前展示的页数list
    private Integer totalPage;
    public void setPagination(Integer totalCount, Integer page, Integer size) {

        if (totalCount / size == 0){
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }

        this.page = page;
        pages.add(page);

        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0, page - i);//往前插入页数
            }
            if(page+i<=totalPage){
                pages.add(page + i);//往后插入页数
            }
        }

        //是否显示上一页按钮
        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //是否显示下一页按钮
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        //是否显示首页按钮
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否显示尾页按钮
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
