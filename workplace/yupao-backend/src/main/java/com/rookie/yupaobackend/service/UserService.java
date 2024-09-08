package com.rookie.yupaobackend.service;

import com.rookie.yupaobackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author zly
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-05-15 23:49:04
*/
public interface UserService extends IService<User> {


    /**
     * 用户注册
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 登录
     * @param userAccount
     * @param userPassword
     * @return
     */
    User doLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 得到安全的User
     * @param user
     * @return
     */
    User getSafetyUser(User user);

    /**
     * 登出--注销
     * @param request
     * @return
     */
    int logout(HttpServletRequest request);

    /**
     * 在内存搜索用户标签搜索
     * @param tags
     * @return
     */


    List<User> searchByTagsByMemory(List<String> tags);

    /**
     * 在sql搜索用户标签
     * @param tags
     * @return
     */
    List<User> searchByTagsBySQL(List<String> tags);
}
