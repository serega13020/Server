package com.example.carmixserver.repository;

import com.example.carmixserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
   // @Query("SELECT o FROM Order o WHERE ((o.start_Task >= ?1 AND o.finish_Task <=?2) or (o.start_Task >=?3 and o.finish_Task <=?4)) and o.planed=TRUE")
    @Query("SELECT o FROM Order o WHERE o.planed = TRUE AND ((o.start_Task BETWEEN ?1 AND ?2) OR (o.finish_Task BETWEEN ?1 AND ?2) OR (o.start_Task BETWEEN ?3 AND ?4) OR (o.finish_Task BETWEEN ?3 AND ?4))")

    List<Order> getOrderByDate(Date startWeek, Date endWeek, Date startMonth, Date endMonth);
}
