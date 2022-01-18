module com.mycompany.comandasinterfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.persistence;
    requires jasperreports;
    
    
    opens com.mycompany.comandasinterfaz to javafx.fxml,javafx.swing, org.hibernate.orm.core, java.sql;
    opens models;
    exports com.mycompany.comandasinterfaz;
    
}
