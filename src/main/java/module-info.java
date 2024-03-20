module org.groupwork.donation {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens org.groupwork.donation to javafx.fxml;
    exports org.groupwork.donation;
    exports org.groupwork.donation.Controllers;
    exports org.groupwork.donation.Controllers.Admin;
    exports org.groupwork.donation.Controllers.Client;
    exports org.groupwork.donation.Models;
    exports org.groupwork.donation.Views;
}