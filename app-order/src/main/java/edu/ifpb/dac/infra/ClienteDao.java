
package edu.ifpb.dac.infra;

import edu.ifpb.dac.entidade.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;

@Stateless
public class ClienteDao {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void adicionar(Cliente cliente) {
        manager.persist(cliente);
    }

    public Cliente editar(Cliente cliente) {
        return manager.merge(cliente);
    }
    
    public void remover(Cliente cliente) {
        manager.remove(cliente);
    }
    
    public Cliente buscar(String email) {
        return manager.createQuery("SELECT p FROM Cliente p WHERE p.email = :email", Cliente.class)
                .setParameter("email", email)
                .getSingleResult();
    }
    
    public List<Cliente> listar() {
        return manager.createQuery("SELECT p FROM Cliente p", Cliente.class).getResultList();
    }
    
}
