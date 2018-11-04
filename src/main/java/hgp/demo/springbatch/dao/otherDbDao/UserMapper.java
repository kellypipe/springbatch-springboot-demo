package hgp.demo.springbatch.dao.otherDbDao;

import hgp.demo.springbatch.model.User;
import hgp.demo.springbatch.model.UserExample;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    List<User> selectAllUsers();

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}