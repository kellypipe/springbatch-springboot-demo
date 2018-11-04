package hgp.demo.springbatch.dao.currentDbDao;

import hgp.demo.springbatch.model.UserTo;
import hgp.demo.springbatch.model.UserToExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserToMapper {
    long countByExample(UserToExample example);

    int deleteByExample(UserToExample example);

    int insert(UserTo record);

    int insertSelective(UserTo record);

    List<UserTo> selectByExample(UserToExample example);

    int updateByExampleSelective(@Param("record") UserTo record, @Param("example") UserToExample example);

    int updateByExample(@Param("record") UserTo record, @Param("example") UserToExample example);
}