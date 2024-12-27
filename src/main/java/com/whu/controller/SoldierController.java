package com.whu.controller;

import com.whu.pojo.dto.PageQueryDTO;
import com.whu.pojo.dto.SoldierDTO;
import com.whu.pojo.vo.PageResult;
import com.whu.pojo.vo.R;
import com.whu.service.SoldierService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soldier")
public class SoldierController {

    @Resource
    private SoldierService soldierService;

    @PostMapping
    public R add(@RequestBody SoldierDTO soldierDTO) {
        soldierService.add(soldierDTO);
        return R.success();
    }

    @GetMapping("/list")
    public R list(PageQueryDTO queryDTO) {
        PageResult pageResult = soldierService.query(queryDTO);
        return R.success(pageResult);
    }

    @GetMapping("/detail")
    public R detail(@RequestParam Long id) {
        if (id == null) return R.error("输入为空");
        return R.success(soldierService.detail(id));
    }
}
