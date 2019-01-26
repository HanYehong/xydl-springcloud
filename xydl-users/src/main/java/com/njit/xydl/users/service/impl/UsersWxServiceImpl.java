package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.dao.UsersWxMapper;
import com.njit.xydl.users.entity.UsersWx;
import com.njit.xydl.users.entity.UsersWxExample;
import com.njit.xydl.users.service.UsersWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersWxServiceImpl implements UsersWxService {

    @Autowired
    private UsersWxMapper usersWxMapper;

    @Override
    public void checkOpenId(String openId, UsersWx user) throws Exception{
        user.setOpenId(openId);  //先给它初始化一个openid，后面不管插入或者更新都用得着
        UsersWx usersWx = selectByOpenId(openId);
        if(usersWx == null){ //如果openId不存在 则进行插入操作
            user.setId(openId);  //id 主键 不能为空 默认为openid
            usersWxMapper.insert(user);
        }else{   //如果有一项不一样的话 则进行更新操作
            user.setId(usersWx.getId());  //设置相同的id 才能根据id进行更新
            if(usersWx.getGender() != user.getGender()
            || (!usersWx.getAvatarUrl().equals(user.getAvatarUrl()))
            || (!usersWx.getCity().equals(user.getCity()))
            || (!usersWx.getCountry().equals(user.getCountry()))
            || (!usersWx.getLanguage().equals(user.getLanguage()))
            || (!usersWx.getNickname().equals(user.getNickname()))
            || (!usersWx.getProvince().equals(user.getProvince()))) {
                usersWxMapper.updateByPrimaryKey(user);
            }
        }
    }

    @Override
    public UsersWx selectByOpenId(String openId) {
        UsersWxExample example = new UsersWxExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<UsersWx> usersWx = usersWxMapper.selectByExample(example);
        return usersWx.size() == 0?null:usersWx.get(0);
    }

    @Override
    public long countByExample(UsersWxExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(UsersWxExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(UsersWx record) {
        return 0;
    }

    @Override
    public int insertSelective(UsersWx record) {
        return 0;
    }

    @Override
    public List<UsersWx> selectByExample(UsersWxExample example) {
        return null;
    }

    @Override
    public UsersWx selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(UsersWx record, UsersWxExample example) {
        return 0;
    }

    @Override
    public int updateByExample(UsersWx record, UsersWxExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UsersWx record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UsersWx record) {
        return 0;
    }
}
