package com.mobiletrain.service;

import com.mobiletrain.domain.Contact;

import java.util.List;

// 通讯录的业务层
public interface ContactService {

    // 查询所有
    List<Contact> queryAll();

    // 分页查询通讯录
    List<Contact> queryAll(int currentPage, int pageSize);

    // 根据一页几条，查询总页数
    int queryPageCount(int pageSize);

    // 根据id删除通讯录
    // 返回值是是否删除成功
    boolean deleteById(String contactId);

    // 根据id查询一个Contact
    Contact queryById(String contactId);

    // 更新一个通讯录记录
    boolean update(Contact contact);

    // 添加一个通讯录记录
    boolean add(Contact contact);
}
