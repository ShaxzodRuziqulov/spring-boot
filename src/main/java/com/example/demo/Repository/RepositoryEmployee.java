package com.example.demo.Repository;

import com.example.demo.Domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEmployee extends JpaRepository<Employee,Long> {
    List<Employee> findByName(String name);

    List<Employee> findByNameAndLastname(String name,String lastName);

    @Query("select e from Employee e where e.name =:name")
    List<Employee> findByNameQuery(@Param("name")String name);

    @Query(value = "select * from Employee e where e.name =:name",nativeQuery = true)
    List<Employee> findByNameQueryNative(@Param("name")String name);

    List<Employee> findAllByNameLike(String name);

    List<Employee> findAllByNameStartingWith(String name);

    List<Employee> findAllByNameEndingWith(String name);

    @Query("select e from Employee e where upper(e.name) like UPPER( concat('%',:name ,'%'))")
    List<Employee> findAllByLike(@Param("name")String name);

    @Query(value = "select * from Employee e where e.name like %:name% ",nativeQuery = true)
    List<Employee> findAllByNativeLike(@Param("name") String name);




}
