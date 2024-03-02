module ch.hftm {
    requires java.logging;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens ch.hftm to javafx.fxml;
    opens ch.hftm.control to javafx.fxml;

    exports ch.hftm;
    exports ch.hftm.control to javafx.fxml;
}
