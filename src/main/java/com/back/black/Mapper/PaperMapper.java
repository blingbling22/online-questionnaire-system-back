package com.back.black.Mapper;

import com.back.black.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.StringTokenizer;

@Mapper
public interface PaperMapper extends BaseMapper<Paper> {

}
