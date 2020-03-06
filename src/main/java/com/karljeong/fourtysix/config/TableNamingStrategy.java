package com.karljeong.fourtysix.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import com.karljeong.fourtysix.utils.CaseUtil;

public class TableNamingStrategy extends SpringPhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String caseToUnderScore = CaseUtil.convertCamelCaseToUnderScore(name.getText());
        return Identifier.toIdentifier(caseToUnderScore);
    }
}
