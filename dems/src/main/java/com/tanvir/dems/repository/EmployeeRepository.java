package com.tanvir.dems.repository;

import com.tanvir.dems.dto.EmployeeRankDto;
import com.tanvir.dems.entity.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN e.department d " +
            "WHERE (:name IS NULL OR e.name = :name) " +
            "AND (:department IS NULL OR d.name = :department) " +
            "AND (:minSalary IS NULL OR e.salary >= :minSalary) " +
            "AND (:maxSalary IS NULL OR e.salary <= :maxSalary)") //JPQL
    List<Employee> findEmployeesWithFilters(@Param("name") String name,
                                            @Param("department") String department,
                                            @Param("minSalary") BigDecimal minSalary,
                                            @Param("maxSalary") BigDecimal maxSalary);

    @Query(value = """
    SELECT e.name AS employeeName, 
           d.name AS departmentName, 
           e.salary AS salary,
           RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) AS rank
    FROM employees e
    JOIN departments d ON e.department_id = d.department_id
    WHERE (:departmentId IS NULL OR e.department_id = :departmentId)
    ORDER BY rank
    """, nativeQuery = true) //native SQL
    List<Object[]> findEmployeeRanksByDepartment(@Param("departmentId") Long departmentId);

    boolean existsByUser_UserId(Long userId);
}
