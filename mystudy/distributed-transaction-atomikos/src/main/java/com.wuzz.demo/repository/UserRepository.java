package com.wuzz.demo.repository;

import com.wuzz.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author ：wuzhenzhao
 * @date ：Created in 2019/1/23 15:47
 */
@Repository
public class UserRepository {

    private final DataSource dataSource;

    private final DataSource masterDataSource;

    private final DataSource salveDataSource;


    @Autowired
    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("salveDataSource")DataSource salveDataSource) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;

        this.salveDataSource = salveDataSource;
    }

    private boolean jdbcSave(User user) {
        boolean success = false;

        System.out.printf("[Thread : %s ] save user :%s\n",
                Thread.currentThread().getName(), user);
        Connection connection = null;
        try {
            connection = masterDataSource.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name) VALUES (?);");
            preparedStatement.setString(1, user.getName());
            success = preparedStatement.executeUpdate() > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }




    public boolean save(User user) {
        System.out.println("User:"+user);
        return true;
    }

    public Collection<User> findAll(){

        return Collections.emptyList();
    }

}

