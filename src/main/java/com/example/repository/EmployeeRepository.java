package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

    // オブジェクトと行を紐づける
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);

    // データベースアクセス用テンプレート
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(Administrator.class);

    /** 全件取得SQL */
    private static String findAllSql = """
            SELECT
            id, name, image, gender, hire_date, mail_address,
            zip_code, address, salary, characteristics, dependents_count
            FROM employees ORDER BY hire_date DESC;
            """;

    /** 主キー検索SQL */
    private static String loadSql = """
            SELECT
            id, name, image, gender, hire_date, mail_address,
            zip_code, address, salary, characteristics, dependents_count
            FROM employees
            WHERE id = :id;
            """;
    /** 従業員情報更新SQL */
    private static String updateSql = """
            UPDATE employees
            SET
                name = :name,
                image = :image,
                gender = :gender,
                hire_date = :hire_date,
                mail_address = :mail_address,
                zip_code = :zip_code,
                address = :address,
                salary = :salary,
                characteristics = :characteristics,
                dependents_count = :dependents_count
            WHERE id = :id;
            """;

    /**
     * 従業員情報を全件取得する
     * 
     * @return employeeList 全従業員一覧
     */
    public List<Employee> findAll() {
        List<Employee> employeeList = namedParameterJdbcTemplate.query(findAllSql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /**
     * 従業員情報を主キー検索する
     * 
     * @param id 検索する従業員ID
     * @return null または employee 従業員情報
     */
    public Employee load(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        // NullPointerExceptionを避けるため、一度リストで受ける
        List<Employee> tmpEmployeeList = namedParameterJdbcTemplate.query(loadSql, param, EMPLOYEE_ROW_MAPPER);

        // 主キー検索でヒットしない場合はnullを返す
        if (tmpEmployeeList == null) {
            return null;
        }

        // ヒットした場合
        return tmpEmployeeList.get(0);
    }

    /**
     * 従業員情報を更新する
     * idで検索し、id以外のカラムを更新する
     * 
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", employee.getId())
                .addValue("name", employee.getName())
                .addValue("image", employee.getImage())
                .addValue("gender", employee.getGender())
                .addValue("hire_date", employee.getHireDate())
                .addValue("mail_address", employee.getMailAddress())
                .addValue("zip_code", employee.getZipCode())
                .addValue("address", employee.getAddress())
                .addValue("telephone", employee.getTelephone())
                .addValue("salary", employee.getSalary())
                .addValue("characteristics", employee.getCharacteristics())
                .addValue("dependents_count", employee.getDependentsCount());
        namedParameterJdbcTemplate.update(updateSql, param);

    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id, name, mail_address, password FROM administrators " +
                "WHERE mail_address = :mailAddress AND password = :password;";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);
        List<Administrator> administrators = namedParameterJdbcTemplate.query(sql, param, ADMINISTRATOR_ROW_MAPPER);

        if (administrators.size() == 0) {
            return null;
        } else {
            return administrators.get(0);
        }
    }
}
