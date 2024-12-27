package com.whu.service;

import com.whu.pojo.dto.PageQueryDTO;
import com.whu.pojo.dto.SoldierDTO;
import com.whu.pojo.entity.Soldier;
import com.whu.pojo.vo.PageResult;

public interface SoldierService {

    void add(SoldierDTO soldierDTO);

    PageResult query(PageQueryDTO queryDTO);

    Soldier detail(Long id);
}
