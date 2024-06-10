package edu.cpp.DeptCrudMvc.dao;

import java.util.List;
import edu.cpp.DeptCrudMvc.entity.Department;

public interface DepartmentDAO {
    // find all Department objects from database table & return them in a list
    public abstract List<Department> findAll();

    // find a Department object by id from database table & return the object
    public abstract Department findById(int id);

    // insert or update a Department object in the database
    public abstract Department save(Department department);

    // delete a Department object by id from the department table in the database
    public abstract void deleteById(int id);
}
