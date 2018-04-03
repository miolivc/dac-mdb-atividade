
package edu.ifpb.dac.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author miolivc
 */

@Entity
@SequenceGenerator(name = "item_pedido_seq", sequenceName = "item_pedido_seq")
public class ItemPedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_pedido_seq")
    private int id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Produto produto;
    private BigDecimal quantidade = BigDecimal.ONE;;

    public ItemPedido(Produto produto) {
        this.produto = produto;
    }

    public ItemPedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return produto.getPreco().multiply(quantidade);
    }
    
    @Override
    public String toString() {
        return "\nItemPedido:" + "produto: " + produto.getDescricao() 
                + " quantidade: " + quantidade + "\n";
    }
    
}
