/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_OrderItem", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o"),
    @NamedQuery(name = "OrderItem.findById", query = "SELECT o FROM OrderItem o WHERE o.id = :id"),
    @NamedQuery(name = "OrderItem.findByQuantity", query = "SELECT o FROM OrderItem o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderItem.findByProductPrice", query = "SELECT o FROM OrderItem o WHERE o.productPrice = :productPrice")})
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "productPrice", nullable = false)
    private double productPrice;
    @JoinColumn(name = "invoice", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Invoice invoice;
    @JoinColumn(name = "product", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product product;

    public OrderItem() {
    }

    public OrderItem(Integer id) {
        this.id = id;
    }

    public OrderItem(Integer id, int quantity, double productPrice) {
        this.id = id;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public OrderItem(int quantity, double productPrice, Invoice invoice, Product product) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.invoice = invoice;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getStringPrice() {
        return String.format("%.2f", productPrice);
    }

    public String getSubTotal() {
        return String.format("%.2f", productPrice * quantity);
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderItem[ id=" + id + " ]";
    }


}
