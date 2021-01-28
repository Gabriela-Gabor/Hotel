package sample;

import controller.AdministratorController;
import controller.ClientController;
import domain.Comanda;
import domain.Produs;
import domain.validator.Validator;
import domain.validator.ValidatorComanda;
import domain.validator.ValidatorProdus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.ComandaRepository;
import repository.ProdusRepository;
import repository.Repository;
import service.Service;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        String produseFile = "data/produse.txt";
        String comenziFile="data/comenzi.txt";

        Validator<Produs> validatorProduse = new ValidatorProdus();
        Validator<Comanda> validatorComenzi = new ValidatorComanda();

        Repository<String, Produs> repoProduse = new ProdusRepository(produseFile, validatorProduse);
        Repository<Integer,Comanda> repoComenzi = new ComandaRepository(comenziFile, validatorComenzi);

        Service service = new Service(repoProduse,repoComenzi);

        //Administrator
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("administrator.fxml"));
        AnchorPane root = loader.load();

        AdministratorController ctrl = loader.getController();
        ctrl.setService(service);


        primaryStage.setTitle("Administrator");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();

        //Client
        Parameters parameters = getParameters();
        for (String camera : parameters.getRaw()) {
            Stage stage = new Stage();
            FXMLLoader l = new FXMLLoader();
            l.setLocation(getClass().getResource("client.fxml"));
            AnchorPane r = l.load();

            ClientController contrl = l.getController();
            contrl.setService(service, camera);


            stage.setTitle("Camera "+camera);
            stage.setScene(new Scene(r, 248, 435));
            stage.show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
