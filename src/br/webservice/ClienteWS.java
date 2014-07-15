/**
 * ClienteWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import br.Cliente.Cliente;
import br.Cliente.ClienteRN;

@WebService
public class ClienteWS {

	public Cliente loginCliente(@WebParam(name = "login") String login,
			@WebParam(name = "senha") String senha) {

		ClienteRN clienteRN = new ClienteRN();

		Cliente cliente = null;
		try {
			cliente = clienteRN.realizarLoginCliente(login, senha);
		} catch (Exception e) {
			System.out.println("Erro ao buscar Cliente: " + e);
		}
		return cliente;
	}
	
	
	
}