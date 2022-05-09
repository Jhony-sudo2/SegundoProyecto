package com.example.demo.controllers;
import com.example.demo.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
    UserService userService;
    //Inicio del juego
    @PostMapping("/Game/start")
    public HttpStatus Start(@RequestBody String Entrada){
        return userService.GuardarCartas(Entrada);
    }

    //Insertar una carta
    @PostMapping("/Game/add")
    public ResponseEntity<String> Add(@RequestBody JSONObject Entrada){
        if(userService.GuardarCarta(Entrada))
        return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/Game/delete")
    public ResponseEntity<String> Eliminar(@RequestBody JSONObject Entrada){

        switch (userService.ELiminar(Entrada)) {
            case 1:
            return new ResponseEntity<>(HttpStatus.OK);
            case 2:
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
            case 3:
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //406
            case 4:
            return new ResponseEntity<>(HttpStatus.CONFLICT); //409
            default:
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
        }
        
    }

    @GetMapping("/Game/avltree")
    public ResponseEntity<JSONObject>getRecorrdio(@RequestParam String transversal){
        int n = 1;
        if(transversal.equals("preOrder")) n = 2;
        else if(transversal.equals("inOrder")) n=1;
        else if(transversal.equals("postOrder")) n=3;
        return new ResponseEntity<JSONObject>(userService.Recorrido(n),HttpStatus.OK);
    }

    @GetMapping("/Game/get-level")
    public ResponseEntity<JSONObject> Prueba(@RequestParam int level){
        
        return new ResponseEntity<JSONObject>(userService.Pruebas(level),HttpStatus.OK);
    }

    @GetMapping("/Game/status-avltree")
    public ResponseEntity<JSONObject> Imagen(){
        return new ResponseEntity<>(userService.getImagen(),HttpStatus.OK);
    }

}
