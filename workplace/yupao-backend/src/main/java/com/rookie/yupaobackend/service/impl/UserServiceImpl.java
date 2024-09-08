package com.rookie.yupaobackend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rookie.yupaobackend.common.ErrorCode;
import com.rookie.yupaobackend.exception.BusinessException;
import com.rookie.yupaobackend.model.domain.User;
import com.rookie.yupaobackend.service.UserService;
import com.rookie.yupaobackend.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.rookie.yupaobackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author zly
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-05-15 23:49:04
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    UserMapper userMapper;
    private static final String SALT="rookie";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短");
        }
        if (userPassword.length()<8||checkPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户密码过短");
        }
        if (planetCode.length()>5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球承载量过大");
        }
        String validPattern = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";;
        Matcher str = Pattern.compile(validPattern).matcher(userAccount);
        if (str.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号重复");
        }
        QueryWrapper<User> queryWrapper2=new QueryWrapper<>();
        queryWrapper.eq("planetCode",planetCode);
        count = userMapper.selectCount(queryWrapper2);
        if (count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球号重复");
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if(!saveResult){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据库插入数据异常");
        }
        return user.getId();
    }

    @Override
    public User doLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String validPattern = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";;
        Matcher str = Pattern.compile(validPattern).matcher(userAccount);
        if (str.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user==null){
            log.info("user login failed,userAccount can't match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户密码不匹配");
        }
        //脱敏
        User safetyUser=getSafetyUser(user);

        //记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);

        return safetyUser;
    }

    public int logout(HttpServletRequest request){
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    @Override
    public User getSafetyUser(User user) {
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setTags(user.getTags());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        return safetyUser;
    }

    @Override
    public List<User> searchByTagsByMemory(List<String> tags){
        if (CollectionUtils.isEmpty(tags)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper queryWrapper=new QueryWrapper<>();
        List<User> list = userMapper.selectList(queryWrapper);
        Gson gson=new Gson();
        return list.stream().filter(user -> {
            String tagsStr=user.getTags();
            if (StringUtils.isBlank(tagsStr)){
                return false;
            }
            Set<String> tempTagsSet= gson.fromJson(tagsStr,new TypeToken<Set<String>>(){}.getType());
            tempTagsSet = Optional.ofNullable(tempTagsSet).orElse(new HashSet<>());
            for (String tagName:
                 tempTagsSet) {
                if (!tempTagsSet.contains(tagName)){
                    return false;
                }
            }
            return true;
        }).map(this::getSafetyUser).collect(Collectors.toList());
    }

    @Deprecated
    public List<User> searchByTagsBySQL(List<String> tags) {
      QueryWrapper queryWrapper=new QueryWrapper<User>();
        for (String tag:tags){
        queryWrapper.like("tags",tag);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        return list;
    }
}




