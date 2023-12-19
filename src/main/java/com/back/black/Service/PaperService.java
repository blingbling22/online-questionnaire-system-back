package com.back.black.Service;

import com.back.black.entity.Paper;
import com.back.black.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface PaperService extends IService<Paper> {

    //插入试卷
    Integer insertPaper(Paper paper);
    Paper getPaperById(Integer id);
}
