package com.achp.dao;

import com.achp.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    @Select("select * from member where id=#{id}")
    Member findById(String id);
}
