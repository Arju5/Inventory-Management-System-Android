package iss.workshop.inventorymanagementsystem.model;

public class Employee {
    public int id;
    public String username;
    public String email;
    public String firstname;
    public String lastname;
    public String password;
    public String phoneNumber;
    public String deptRep;
    public Employee supervisedBy;
    public EmployeeType employeeType;
    public Department department;
    public EmployeeType tempDeptHeadType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeptRep() {
        return deptRep;
    }

    public void setDeptRep(String deptRep) {
        this.deptRep = deptRep;
    }

    public Employee getSupervisedBy() {
        return supervisedBy;
    }

    public void setSupervisedBy(Employee supervisedBy) {
        this.supervisedBy = supervisedBy;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeType getTempDeptHeadType() {
        return tempDeptHeadType;
    }

    public void setTempDeptHeadType(EmployeeType tempDeptHeadType) {
        this.tempDeptHeadType = tempDeptHeadType;
    }
}
