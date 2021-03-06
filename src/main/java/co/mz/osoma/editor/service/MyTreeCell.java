/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.mz.osoma.editor.service;

import co.mz.osoma.editor.modelo.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;

import co.mz.osoma.editor.controlador.MainGUIController;

/**
 * @author Lenovo
 */
public class MyTreeCell extends TextFieldTreeCell<Object> {

    private ContextMenu questionMenu, examMenu, choiceMenu, collectionMenu;
    private MainGUIController controller;

    public MyTreeCell(MainGUIController mainGUIController) {

        this.controller = mainGUIController;

        MenuItem menu1 = MenuItemBuilder.create().text("Multi Choice (Single Select)").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();
                            questionMultiChoice.setQtype(QuestionType.SIGLE);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }
                    }
                }
        ).build();
        MenuItem menu2 = MenuItemBuilder.create().text("Multi Choice (Multiple Select)").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();
                            questionMultiChoice.setQtype(QuestionType.MULTI);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }
                    }
                }
        ).build();
        MenuItem menu3 = MenuItemBuilder.create().text("Multi Choice (Single Select) With Case of Study").onAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        try {


                            QuestionMultiChoiceCaseStudy questionMultiChoice = new QuestionMultiChoiceCaseStudy();
                            questionMultiChoice.setQtype(QuestionType.SIGLECASESTUDY);
                            ((Exam)mainGUIController.getSeletedItem().getValue()).getQuestions().add(questionMultiChoice);

                            TreeItem<Object> node = mainGUIController.makeBranch(questionMultiChoice, mainGUIController.getSeletedItem());

                            Helper.totalChoices = 0;

                            for (int i = 0; i<questionMultiChoice.getTotalOfChoices(); i++){
                                Choice choice = new Choice();
                                questionMultiChoice.getChoices().add(choice);
                                mainGUIController.makeBranch(choice, node);
                            }

                        }catch (Exception e){

                        }

                    }
                }
        ).build();
        MenuItem menu4 = MenuItemBuilder.create().text("Multi Choice (Multiple Select) With Case of Study").build();
        examMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuBuilder.create().text("New Question").items(menu1, menu2, menu3, menu4).build(),
                        MenuItemBuilder.create()
                                .text("preview")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                try {
                                                    Exam exam = (Exam)mainGUIController.getSeletedItem().getValue();
                                                    for (Question q: exam.getQuestions()) {
                                                        System.out.println(q.getQuestion());
                                                        switch (q.getQtype()){
                                                            case SIGLE:{
                                                                QuestionMultiChoice qmc= (QuestionMultiChoice)q;
                                                                for(Choice c: qmc.getChoices()){
                                                                    System.out.print(" "+c.getDescription()+", ");
                                                                }
                                                            }break;
                                                            case SIGLECASESTUDY:{
                                                                QuestionMultiChoiceCaseStudy qmccs= (QuestionMultiChoiceCaseStudy)q;
                                                                for(Choice c: qmccs.getChoices()){
                                                                    System.out.print(" "+c.getDescription()+", ");
                                                                }
                                                            }break;

                                                        }

                                                    }

                                                }catch (Exception e){

                                                }
                                            }
                                        }
                                ).build(),
                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                try {
                                                    Exam exam = new Exam();
                                                    ((RootObject)mainGUIController.getSeletedItem().getValue()).getExams().add(exam);
                                                    TreeItem<Object> node = mainGUIController.makeBranch(exam, mainGUIController.getSeletedItem());
                                                    Helper.totalQuestions = 0;

                                                }catch (Exception e){

                                                }
                                            }
                                        }
                                ).disable(true)
                                .build()
                )
                .build();

        choiceMenu = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                                ((QuestionMultiChoice)objectTreeItem.getParent().getValue()).getChoices().remove((Choice) objectTreeItem.getValue());
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);


                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();

        questionMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("Add Choice")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {

                                                Helper.totalChoices = ((QuestionMultiChoice)mainGUIController.getSeletedItem().getValue()).getChoices().size()-1;

                                                Choice choice = new Choice();
                                                ((QuestionMultiChoice)mainGUIController.getSeletedItem().getValue()).getChoices().add(choice);
                                                mainGUIController.makeBranch(new Choice(), mainGUIController.getSeletedItem());
                                            }
                                        }
                                )
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Up")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Move Down")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }
                                        }
                                ).disable(true)
                                .build(),
                        MenuItemBuilder.create()
                                .text("Properties")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                            }

                                        }
                                )
                                .build(),

                        MenuItemBuilder.create()
                                .text("Delete")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                TreeItem<Object> objectTreeItem = mainGUIController.getSeletedItem();
                                               ((Exam)objectTreeItem.getParent().getValue()).getQuestions().remove((Question)objectTreeItem.getValue());
                                                boolean remove = objectTreeItem.getParent().getChildren().remove(objectTreeItem);
                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();

        collectionMenu
                = ContextMenuBuilder.create()
                .items(
                        MenuItemBuilder.create()
                                .text("New Exam")
                                .onAction(
                                        new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent arg0) {
                                                RootObject rootObject = (RootObject) mainGUIController.getSeletedItem().getValue();
                                                Exam exam = new Exam();
                                                rootObject.getExams().add(exam);
                                                TreeItem<Object> node = mainGUIController.makeBranch(exam, mainGUIController.getSeletedItem());
                                                //System.out.println(rootObject.toString());
                                            }
                                        }
                                )
                                .build()
                        // other menu item


                )
                .build();


    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);


      if (!empty && item instanceof RootObject) {
          setContextMenu(collectionMenu);
          return;
      }

        if(!empty && item instanceof Exam){
            setContextMenu(examMenu);
            return;
        }

        if(!empty && item instanceof Question){
            setContextMenu(questionMenu);
            return;
        }

        if(!empty && item instanceof Choice){
            setContextMenu(choiceMenu);
            return;
        }
//        setContextMenu(questionMenu);
    }
}
