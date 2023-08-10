package com.example.domain;

import java.time.LocalDate;

public class Employee {
    /**ID */
    private Integer id;
    /** 名前 */
    private String name;
    /** 印象 */
    private String image;
    /** 性別*/
    private String gender;
    /** 雇用日 */
    private LocalDate hireDate;
    /** メールアドレス */
    private String mailAddress;
    /** 郵便番号 */
    private String zipCode;
    /** 住所 */
    private String address;
    /** 電話番号 */
    private Integer telephone;
    /** 給料 */
    private Integer salary;
    /** 扶養家族数 */
    private Integer dependentsCount;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getTelephone() {
        return telephone;
    }
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public Integer getDependentsCount() {
        return dependentsCount;
    }
    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
                + hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address
                + ", telephone=" + telephone + ", salary=" + salary + ", dependentsCount=" + dependentsCount + "]";
    }
}
