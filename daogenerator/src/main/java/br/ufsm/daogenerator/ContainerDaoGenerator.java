package br.ufsm.daogenerator;

/**
 *
 * Criado por Emilio 11/2016
 */

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ContainerDaoGenerator {


    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "br.ufsm.coletaseletivaufsm.containers");

        Entity containers = schema.addEntity("Containers");
        containers.addIdProperty().autoincrement();
        containers.addStringProperty("nome");
        containers.addDoubleProperty("latitude");
        containers.addDoubleProperty("longitude");
        containers.addStringProperty("descricao");
        containers.addBooleanProperty("segunda");
        containers.addBooleanProperty("terca");
        containers.addBooleanProperty("quarta");
        containers.addBooleanProperty("quinta");
        containers.addBooleanProperty("sexta");
        containers.addStringProperty("linkfoto");

        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }
}
