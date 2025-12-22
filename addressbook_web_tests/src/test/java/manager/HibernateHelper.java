package manager;

import manager.hbm.GroupRecord;
import manager.hbm.UserRecord;
import model.GrData;
import model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.JdbcSettings;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserRecord.class);
        configuration.addAnnotatedClass(GroupRecord.class);
        String url = "jdbc:mysql://localhost/addressbook" +
                "?zeroDateTimeBehavior=CONVERT_TO_NULL" +
                "&sslMode=DISABLED";
        configuration.setProperty(JdbcSettings.JAKARTA_JDBC_URL, url);
        configuration.setProperty(JdbcSettings.JAKARTA_JDBC_USER, "root");
        configuration.setProperty(JdbcSettings.JAKARTA_JDBC_PASSWORD, "");
        configuration.setProperty(JdbcSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        try {
            sessionFactory = configuration.buildSessionFactory(registry);
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }
    static List<GrData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convertGroup).collect(Collectors.toList());
        }

    static List<UserData> convertUserList(List<UserRecord> records) {
        return records.stream().map(HibernateHelper::convertUser).collect(Collectors.toList());
    }

    private static UserData convertUser(UserRecord record) {
        return new UserData("" + record.id, record.firstname, record.middlename, record.lastname, record.address, record.home, record.work, record.mobile, record.email, record.email2, record.email3, record.photo);
    }

    private static UserRecord convertUser(UserData userData) {
        var id = userData.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new UserRecord(Integer.parseInt(id), userData.name(),userData.middlename(), userData.lastname(), userData.address(), userData.home(),userData.work(),userData.mobile(),userData.email(),userData.email2(),userData.email3(),userData.photo());
    }


    private static GrData convertGroup(GroupRecord record) {
        return new GrData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convertGroup(GrData grData) {
        var id = grData.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), grData.name(), grData.header(), grData.footer());
    }

    public List<GrData> getGroupList() {
        try (Session session = sessionFactory.openSession()) {
            return convertGroupList(session.createSelectionQuery("from GroupRecord", GroupRecord.class)
                    .getResultList());
        }
    }

    public List<UserData> getUserList() {
        try (Session session = sessionFactory.openSession()) {
            return convertUserList(session.createSelectionQuery("from UserRecord", UserRecord.class)
                    .getResultList());
        }
    }

    public long getGroupCount() {
        try (Session session = sessionFactory.openSession()) {
            return session.createSelectionQuery("select count(g) from GroupRecord g", Long.class)
                    .getSingleResult();
        }
    }

    public long getUserCount() {
        try (Session session = sessionFactory.openSession()) {
            return session.createSelectionQuery("select count(u) from UserRecord u", Long.class)
                    .getSingleResult();
        }
    }

    public void createGroup(GrData grData) {
        sessionFactory.inSession(session -> {
            session.beginTransaction();
            session.persist(convertGroup(grData));
            session.getTransaction().commit();
        });
    }

    public void createUser(UserData userData) {
        sessionFactory.inSession(session -> {
            session.beginTransaction();
            session.persist(convertUser(userData));
            session.getTransaction().commit();
        });
    }




}
