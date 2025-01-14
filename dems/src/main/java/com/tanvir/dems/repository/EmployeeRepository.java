package com.tanvir.dems.repository;

import com.tanvir.dems.entity.Employee;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR e.name = :name) AND " +
            "(:department IS NULL OR e.department.name = :department) AND " +
            "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
            "(:maxSalary IS NULL OR e.salary <= :maxSalary)")
    List<Employee> findEmployeesWithFilters(
            @Param("name") String name,
            @Param("department") String department,
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary") BigDecimal maxSalary);
}
