module gui {
	requires transitive javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires transitive javafx.base;
	requires transitive javafx.graphics;

	exports gui to javafx.graphics;

	opens gui to javafx.fxml, javafx.base;
}