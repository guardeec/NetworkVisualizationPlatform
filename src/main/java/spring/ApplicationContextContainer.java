package spring;

import org.springframework.beans.factory.BeanFactory;

/**
 * Created by Guardeec on 28.04.16.
 */
public class ApplicationContextContainer {
    private static ApplicationContextContainer ourInstance = new ApplicationContextContainer();
    private static BeanFactory factory;

    public static ApplicationContextContainer getInstance() {
        return ourInstance;
    }

    private ApplicationContextContainer() {
    }

    public void setFactory(BeanFactory factory){
        System.out.println("FACTORY INITIALIZATION");
        this.factory = factory;
    }

    public BeanFactory getFactory() {
        return factory;
    }
}
