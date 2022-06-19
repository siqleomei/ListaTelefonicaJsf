/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Usuario
 */
public class ErroSistema extends Exception {
    
    private String message;
    
    public ErroSistema(String msg) {
        super();
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
