/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import domain.Login;

/**
 *
 * @author Developer
 */
public interface IAuthenticationSvc extends IService
{
     public final String  NAME = "IAuthenticationSvc";
     
    public Boolean validateUser(Login login) throws Exception;
    
}
