package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.*;

public class HibernateUtil {
    private static final SessionFactory factory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Transportation.class)
                    .addAnnotatedClass(TouristAttraction.class)
                    .addAnnotatedClass(Schedule.class)
                    .addAnnotatedClass(Route.class)
                    .addAnnotatedClass(PassengerInformation.class)
                    .addAnnotatedClass(Image.class)
                    .addAnnotatedClass(Flight.class)
                    .addAnnotatedClass(Booking.class)
                    .addAnnotatedClass(Accommodation.class)
                    .buildSessionFactory();
        } catch (Throwable throwable)
        {
            throw new ExceptionInInitializerError(throwable);
        }
    }
    public static SessionFactory getSessionFactory(){
        return factory;
    }
    public static void shutdown()
    {
        getSessionFactory().close();
    }

}