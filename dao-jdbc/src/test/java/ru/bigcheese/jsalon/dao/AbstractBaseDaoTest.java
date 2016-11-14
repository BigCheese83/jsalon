package ru.bigcheese.jsalon.dao;

import org.h2.jdbcx.JdbcDataSource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RunWith(Arquillian.class)
public abstract class AbstractBaseDaoTest {

    private static final JdbcDataSource dataSource = new JdbcDataSource();
    protected static long countAll;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage("ru.bigcheese.jsalon.dao")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @BeforeClass
    public static void init() throws Exception {
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        runScript(dataSource, "META-INF/create.sql");
        runScript(dataSource, "META-INF/insert.sql");
    }

    @AfterClass
    public static void close() throws Exception {
        runScript(dataSource, "META-INF/drop.sql");
    }

    @Before
    public void before() {
        countAll = getDao().countAll();
    }

    private static void runScript(DataSource dataSource, String filename) throws Exception {
        List<String> queries = parseSQLQueries(filename);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            for (String query : queries) {
                statement.executeUpdate(query);
            }
            statement.executeBatch();
        }
    }

    private static List<String> parseSQLQueries(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream stream = AbstractBaseDaoTest.class.getClassLoader().getResourceAsStream(filename);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        List<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(sb.toString(), ";", false);
        while (tokenizer.hasMoreTokens()) {
            String query = tokenizer.nextToken().replaceAll("[\\n\\t\\r]", " ");
            result.add(query);
        }
        return result;
    }

    protected abstract BaseDao getDao();
}
