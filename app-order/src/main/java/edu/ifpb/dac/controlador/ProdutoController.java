
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.ProdutoDao;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alexalins
 * @author miolivc
 */
@Named
@RequestScoped
public class ProdutoController {
    
    @Inject
    private ProdutoDao produtos;
    private Produto produto = new Produto();

    public List<Produto> todosOsProdutos() {
        return Collections.unmodifiableList(produtos.listar());
    }
    
    public String adicionarProduto() {
        produtos.adicionar(produto);
        produto = new Produto();
        return "../index.html";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
