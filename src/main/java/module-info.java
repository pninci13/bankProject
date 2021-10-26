module com.visual.bankproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.visual.bankproject to javafx.fxml;
    exports com.visual.bankproject;

//    opens com.visual.bankproject.controllers to javafx.fxml;
//    exports com.visual.bankproject.controllers;

//    opens com.visual.bankproject.controllers.managerControllers to javafx.fxml;
//    exports com.visual.bankproject.controllers.managerControllers;

//    opens com.visual.bankproject.controllers.clientControllers to javafx.fxml;
//    exports com.visual.bankproject.controllers.clientControllers;
//
//    opens com.visual.bankproject.controllers.managerControllers to javafx.fxml;
//    exports com.visual.bankproject.controllers.managerControllers;

//    exports com.visual.bankproject.controllers;
//    opens com.visual.bankproject.controllers to javafx.fxml;

}