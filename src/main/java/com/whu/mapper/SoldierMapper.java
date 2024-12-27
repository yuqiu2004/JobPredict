package com.whu.mapper;

import com.github.pagehelper.Page;
import com.whu.pojo.entity.Soldier;
import com.whu.pojo.vo.SoldierVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SoldierMapper {

    @Insert("insert into soldier (name, gender, birth, birth_place, resettlement, img, military_positions, experience) " +
            "values (#{name}, #{gender}, #{birth}, #{birthPlace}, #{resettlement}, #{img}, #{militaryPositions}, #{experience})")
    void insert(Soldier soldier);

    Page<SoldierVO> pageQuery();

    @Select("select * from soldier where id = #{id}")
    Soldier selectById(Long id);
}
