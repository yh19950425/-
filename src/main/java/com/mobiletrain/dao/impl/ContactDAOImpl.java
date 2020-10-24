package com.mobiletrain.dao.impl;

import com.mobiletrain.dao.ContactDAO;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.utils.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("contactDAO")
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> queryAll() {
        List<Contact> result = null;
        try {
            String sql = "SELECT * FROM contact_info WHERE del=0";

            result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Contact> queryAll(int pageOffset, int pageSize) {
        List<Contact> result = null;
        try {
            String sql = "SELECT * FROM contact_info WHERE del=0 LIMIT ?,?";

            result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), pageOffset, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int queryCount() {
        int result = 0;
        try {
            String sql = "SELECT COUNT(*) FROM contact_info WHERE del=0";

            result = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteById(String contactId) {
        int result = 0;
        try {
            String sql = "UPDATE contact_info SET del=1 WHERE id=?";
            result = jdbcTemplate.update(sql, contactId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Contact queryById(String contactId) {
        String sql = "SELECT * FROM contact_info WHERE id=? AND del=0";

        // query是查多个，返回List，推荐使用
        // queryForObject是查1个，返回javabean。如果通过sql语句，一条都查不到的话，那么会抛异常
        List<Contact> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), contactId);

        if (query.size() == 1) {
            return query.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int update(Contact contact) {
        String sql = "UPDATE contact_info SET name=?, gender=?, birthday=?, birthplace=?, mobile=?, email=? WHERE id=?";

        return jdbcTemplate.update(sql,
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail(),
                contact.getId());
    }

    @Override
    public int add(Contact contact) {
        String sql = "INSERT INTO contact_info(`name`, `gender`, `birthday`, `birthplace`, `mobile`, `email`) VALUES(?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail());
    }
}
