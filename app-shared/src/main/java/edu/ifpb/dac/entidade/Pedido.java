package edu.ifpb.dac.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Produto> produtos;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public BigDecimal valorPedido() {
        return  produtos.parallelStream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
//        BigDecimal valorTotal = new BigDecimal(0);
//        produtos.forEach((produto) -> {
//            valorTotal.add(produto.getPreco());
//        });
        
//        Long collect = produtos.stream()
//                .flatMap((Produto t) -> Stream.of(t.getPreco()))
//                .collect(Collectors.reducing();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(Produto produto) {
        this.produtos.add(produto);
    }

    public void remove(Produto produto) {
        this.produtos.remove(produto);
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "\nPedido:\n" + "id=" + id + "\nprodutos=" + produtos + 
                "\nValor total:" + this.valorPedido() + "\ncliente=" + cliente + '}';
    }

}
