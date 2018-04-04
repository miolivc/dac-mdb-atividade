
package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.infra.ClienteDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Clientes {
    
    @Inject
    private ClienteDao repository;
    
    public void adicionar(Cliente cliente) {
        repository.adicionar(cliente);
    }

    public Cliente editar(Cliente cliente) {
        return repository.editar(cliente);
    }
    
    public void remover(Cliente cliente) {
        repository.remover(cliente);
    }
    
    public Cliente buscar(String email) {
        return repository.buscar(email);
    }
    
    public List<Cliente> listar() {
        return repository.listar();
    }
    
}
