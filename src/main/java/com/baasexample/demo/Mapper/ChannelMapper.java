package com.baasexample.demo.Mapper;

import com.baasexample.demo.model.dbMo.ChannelInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 注释写在这
 *
 * @author Monhey
 */
@Mapper
@Repository
public interface ChannelMapper {
    //Insert
    @Insert("insert into channel (ChannelName,NetId,PeerList) values (#{ChannelName},#{NetId},#{PeerList})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public void insertChannel(ChannelInfo channelInfo);

    //QueryById
    @Select("select * from channel where id = #{id}")
    public ChannelInfo findChannelById(@Param("id") int id);

    @Select("select * from channel")
    public List<ChannelInfo> findAllChannel();
}
