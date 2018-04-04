
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.service.Clientes;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ClienteController {
    
    @Inject
    private Clientes clientes;
    private Cliente cliente = new Cliente();
    
    public String salvarCliente() {
        clientes.adicionar(cliente);
        return UrlDispatcher.dispatch("index.html");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
