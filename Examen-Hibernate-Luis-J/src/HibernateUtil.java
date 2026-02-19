import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            // Cargamos la configuración desde hibernate.cfg.xml
            sessionFactory = new
                    Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Fallo al crear la SessionFactory: "
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    // Para obtener la factoría de sesiones
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    // Para cerrar Hibernate al apagar la app
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}