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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_SuggestProduct", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuggestProduct.findAll", query = "SELECT s FROM SuggestProduct s"),
    @NamedQuery(name = "SuggestProduct.findById", query = "SELECT s FROM SuggestProduct s WHERE s.id = :id"),
    @NamedQuery(name = "SuggestProduct.findByMainProduct", query = "SELECT s FROM SuggestProduct s WHERE s.mainProduct = :mainProduct"),
    @NamedQuery(name = "SuggestProduct.findBySuggestProduct", query = "SELECT s FROM SuggestProduct s WHERE s.suggestProduct = :suggestProduct")})
public class SuggestProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "mainProduct", nullable = false)
    private int mainProduct;
    @Basic(optional = false)
    @Column(name = "suggestProduct", nullable = false)
    private int suggestProduct;

    public SuggestProduct() {
    }

    public SuggestProduct(Integer id) {
        this.id = id;
    }

    public SuggestProduct(Integer id, int mainProduct, int suggestProduct) {
        this.id = id;
        this.mainProduct = mainProduct;
        this.suggestProduct = suggestProduct;
    }

    public SuggestProduct(int mainProduct, int suggestProduct) {
        this.mainProduct = mainProduct;
        this.suggestProduct = suggestProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(int mainProduct) {
        this.mainProduct = mainProduct;
    }

    public int getSuggestProduct() {
        return suggestProduct;
    }

    public void setSuggestProduct(int suggestProduct) {
        this.suggestProduct = suggestProduct;
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
        if (!(object instanceof SuggestProduct)) {
            return false;
        }
        SuggestProduct other = (SuggestProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SuggestProduct[ id=" + id + " ]";
    }

}
