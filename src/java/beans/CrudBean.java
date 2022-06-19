/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import dao.CrudDao;

public abstract class CrudBean<E, D extends CrudDao> {
   
    private E entidade;   
    
    public abstract D getDao();
    public abstract E criarNovaEntidade();
    
    
}
