package edu.cpp.DeptCrudMvc.dao;

import edu.cpp.DeptCrudMvc.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    private EntityManager entityManager;

    // Retrieve all departments from the database
    @Override
    public List<Department> findAll() {
        TypedQuery<Department> query = entityManager.createQuery("SELECT d FROM Department d", Department.class);
        return query.getResultList();
    }

    // Find a department by its ID
    @Override
    public Department findById(int id) {
        return entityManager.find(Department.class, id);
    }

    // Save a new department or update an existing one
    @Override
    @Transactional
    public Department save(Department department) {
        return entityManager.merge(department);
    }













    

    // Delete a department by its ID
    @Override
    @Transactional
    public void deleteById(int id) {
        Department department = entityManager.find(Department.class, id);
        if (department != null) {
            entityManager.remove(department);
        }
    }
}
