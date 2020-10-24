package com.mobiletrain.dao;

import com.mobiletrain.domain.Contact;

import java.util.List;

// 通讯录整合层接口
public interface ContactDAO {

    // 查询所有
    List<Contact> queryAll();

    // 分页查询所有
    List<Contact> queryAll(int pageOffset, int pageSize);

    // 查询记录条数
    int queryCount();

    // 根据id删除，返回受影响的记录条数
    int deleteById(String contactId);

    // 根据id查Contact
    Contact queryById(String contactId);

    // 更新通讯录记录
    int update(Contact contact);

    // 添加通讯录记录
    int add(Contact contact);
}
