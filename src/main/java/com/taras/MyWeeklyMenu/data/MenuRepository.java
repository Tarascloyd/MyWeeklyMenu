package com.taras.MyWeeklyMenu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taras.MyWeeklyMenu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
