/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;


import com.google.gson.JsonObject;

/**
 *
 * @author PCcasa
 */
public class CrearJSON {
    
     public static void main(String[] args){
         ElementsJSON element1 = new ElementsJSON("toni", "454554", 3435);
         ElementsJSON element2 = new ElementsJSON("toni", "435", 6768);
         
         JsonObject object = new JsonObject();         
         object.addProperty("nom", element1.getNom());
         object.addProperty("id", element1.getIdentificador());
         object.addProperty("total", element1.getTotalNomina());
         
         String json  = object.toString();
         
         System.out.println(json);
         
         
     }
    
}
