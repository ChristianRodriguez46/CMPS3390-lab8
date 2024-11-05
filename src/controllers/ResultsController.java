package controllers;

import views.ResultsView;

public class ResultsController {
    ResultsController(ResultsView view){
        view.attachOKListener(e -> view.dispose());
    }
}
