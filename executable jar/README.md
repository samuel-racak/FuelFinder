# Running FuelFinder.jar

To run the `FuelFinder.jar` file, you need to use the following command in the terminal:

java --module-path “<path-to-javafx-sdk>/lib” --add-modules “javafx.controls,javafx.fxml” -jar FuelFinder.jar


Make sure to replace `<path-to-javafx-sdk>` with the actual path to your JavaFX SDK installation. For example, if your JavaFX SDK is installed in `C:/Program Files/Java/javafx-sdk-17.0.6`, the command would be:

java --module-path “C:/Program Files/Java/javafx-sdk-17.0.6/lib” --add-modules “javafx.controls,javafx.fxml” -jar FuelFinder.jar


**Note:** The `FuelFinder.jar` file needs to have access to the `/src/resources` folder in the current directory in order to work correctly.