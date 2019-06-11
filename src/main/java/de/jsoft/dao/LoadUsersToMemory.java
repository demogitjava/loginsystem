package de.jsoft.dao;

import de.jsoft.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sysadmin on 27.05.19.
 */
@Repository
public class LoadUsersToMemory
{

    @Autowired
    private JdbcTemplate jtm;

    public List<Users> getAllUsers()
    {
        List<Users> demousers = jtm.query("select * from userdata", new BeanPropertyRowMapper(Users.class));
        return demousers;
    }


    public Users getItembyId(int id)
    {
        Users users = (Users) jtm.query("SELECT * FROM USERS WHERE ID=?", new BeanPropertyRowMapper(Users.class));
        return users;
    }
}
