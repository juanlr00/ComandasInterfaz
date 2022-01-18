module com.mycompany.comandasinterfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;
    requires jasperreports;
    requires javafx.swing;
   

    opens com.mycompany.comandasinterfaz to javafx.fxml,org.hibernate.orm.core,javafx.swing, java.sql;
    opens models;
    exports com.mycompany.comandasinterfaz;
    
}
