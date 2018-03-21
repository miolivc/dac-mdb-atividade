
package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.ProdutoDao;
import java.util.List;
import java.util.Random;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class Vitrine {
    
    @Inject
    private ProdutoDao produtoDao;
    
    public Produto escolherProduto() {
        List<Produto> produtos = produtoDao.listar();
        int random = new Random(produtos.size()).nextInt();
        return produtos.get(random);
    }
    
}
