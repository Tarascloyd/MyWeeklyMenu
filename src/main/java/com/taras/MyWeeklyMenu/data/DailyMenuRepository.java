package com.taras.MyWeeklyMenu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taras.MyWeeklyMenu.entity.DailyMenu;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long>{

}
