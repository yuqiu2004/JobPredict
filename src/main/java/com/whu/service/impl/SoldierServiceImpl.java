package com.whu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whu.mapper.SoldierMapper;
import com.whu.pojo.dto.PageQueryDTO;
import com.whu.pojo.dto.SoldierDTO;
import com.whu.pojo.entity.Soldier;
import com.whu.pojo.vo.PageResult;
import com.whu.pojo.vo.SoldierVO;
import com.whu.service.SoldierService;
import com.whu.utils.MinioUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldierServiceImpl implements SoldierService {

    @Resource
    private SoldierMapper soldierMapper;

    @Resource
    private MinioUtil minioUtil;

    @Override
    public void add(SoldierDTO soldierDTO) {
        Soldier soldier = new Soldier();
        BeanUtils.copyProperties(soldierDTO, soldier);
        soldierMapper.insert(soldier);
    }

    @Override
    public PageResult query(PageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        Page<SoldierVO> page = soldierMapper.pageQuery();
        List<SoldierVO> result = page.getResult();
        result.forEach( soldierVO -> soldierVO.setImg(minioUtil.preview(soldierVO.getImg())) );
        return new PageResult(page.getTotal(), result);
    }

    @Override
    public Soldier detail(Long id) {
        Soldier soldier = soldierMapper.selectById(id);
        soldier.setImg(minioUtil.preview(soldier.getImg()));
        return soldier;
    }
}
