package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    // JDBCテンプレートを生成
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;
    // RowMapperを定義
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(Administrator.class);

    // 管理者情報を挿入する
    public void insert(Administrator administrator) {

        String sql = "INSERT INTO administrators(name,mail_address,password) " +
                "(:name, :mailAddress, :password);";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", administrator.getName())
                .addValue("mailAddress", administrator.getMailAddress())
                .addValue("password", administrator.getPassword());

        // idが渡されていない場合は、INSERTし
        // idが渡されている場合は、UPDATEする
        if (administrator.getId() == null) {
            namedTemplate.update(sql, param);
        } else {
            sql = "UPDATE administrators SET " +
                    "name = :name, mail_address = :mailAddress, password = :password " +
                    "WHERE id = :id;";
            param = new MapSqlParameterSource()
                    .addValue("id", administrator.getId())
                    .addValue("name", administrator.getName())
                    .addValue("mailAddress", administrator.getMailAddress())
                    .addValue("password", administrator.getPassword());
            namedTemplate.update(sql, param);
        }
    }

}
