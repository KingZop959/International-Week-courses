package com.rookie.yupaobackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.yupaobackend.model.domain.Tag;
import com.rookie.yupaobackend.service.TagService;
import com.rookie.yupaobackend.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author zly
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2023-05-15 23:53:57
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




