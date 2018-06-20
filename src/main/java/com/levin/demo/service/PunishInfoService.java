package com.levin.demo.service;

import com.levin.demo.bean.PunishInfo;
import com.levin.demo.mapper.PunishInfoMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PunishInfoService {
    @Autowired
    private PunishInfoMapper punishInfoMapper;//这里会报错，但是并不会影响

    public List<PunishInfo> listPunishInfo(){
        return punishInfoMapper.listPunishInfo();
    }

}
