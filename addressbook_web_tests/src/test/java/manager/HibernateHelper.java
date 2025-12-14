package manager;

import manager.hbm.GroupRecord;
import model.GrData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GrData> converList(List<GroupRecord> records) {
        List<GrData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GrData convert(GroupRecord record) {
        return new GrData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GrData grData) {
        var id = grData.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), grData.name(), grData.header(), grData.footer());
    }

    public List<GrData> getGroupList() {
        return converList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", long.class).getSingleResult();
        });
    }

    public void createGroup(GrData grData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(grData));
            session.getTransaction().commit();
        });
    }
}
