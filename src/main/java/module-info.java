module com.visual.bankproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.visual.bankproject to javafx.fxml;
    exports com.visual.bankproject;
}