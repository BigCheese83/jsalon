#!/bin/bash
asadmin create-auth-realm \
        --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm \
        --property jaas-context=jdbcRealm:datasource-jndi=jdbc/salon:user-table=v_user_role:user-name-column=username:password-column=password:group-table=v_user_role:group-table-user-name-column=username:group-name-column=role:digestrealm-password-enc-algorithm=MD5:digest-algorithm=MD5 \
        salonJdbcRealm;
asadmin list-auth-realms;