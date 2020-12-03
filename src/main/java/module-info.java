module org.mss {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens org.mss to javafx.fxml;
    opens org.mss.controller to javafx.fxml;
    opens org.mss.controller.customer to javafx.fxml, javafx.base;
    opens org.mss.controller.admin to javafx.fxml;
    opens org.mss.controller.user to javafx.fxml;
    opens org.mss.controller.data to javafx.fxml, javafx.base;
    exports org.mss;
}