package be.ehb.spg3;
// Created by Wannes Gennar. All rights reserved


import be.ehb.spg3.contracts.encryption.Encryptor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static be.ehb.spg3.providers.InjectionProvider.resolve;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		String encryptedPass = resolve(Encryptor.class).encrypt("Deze wordt geencrypteerd...");
		System.out.println(encryptedPass);
		String decryptedPass = resolve(Encryptor.class).decrypt(encryptedPass);
		System.out.println(decryptedPass);
		Parent root = FXMLLoader.load(getClass().getResource("auth/login/login.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setScene(new Scene(root, 618, 480));
		primaryStage.show();
	}
}