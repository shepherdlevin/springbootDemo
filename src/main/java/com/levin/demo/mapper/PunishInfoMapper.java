package com.levin.demo.mapper;

import com.levin.demo.bean.PunishInfo;
import java.util.List;

public interface PunishInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PunishInfo record);

    int insertSelective(PunishInfo record);

    PunishInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PunishInfo record);

    int updateByPrimaryKey(PunishInfo record);

    List<PunishInfo> listPunishInfo();
}