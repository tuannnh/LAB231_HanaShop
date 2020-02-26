/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_Coupon", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupon.findAll", query = "SELECT c FROM Coupon c"),
    @NamedQuery(name = "Coupon.findAllAvailable", query = "SELECT c FROM Coupon c WHERE c.status.statusName = 'Active'"),
    @NamedQuery(name = "Coupon.findByCoupon", query = "SELECT c FROM Coupon c WHERE c.coupon = :coupon"),
    @NamedQuery(name = "Coupon.findByDiscount", query = "SELECT c FROM Coupon c WHERE c.discount = :discount")})
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "coupon", nullable = false, length = 150)
    private String coupon;
    @Basic(optional = false)
    @Column(name = "discount", nullable = false)
    private double discount;
    @OneToMany(mappedBy = "coupon")
    private List<Invoice> invoiceList;
    @JoinColumn(name = "status", referencedColumnName = "statusName", nullable = false)
    @ManyToOne(optional = false)
    private Status status;

    public Coupon() {
    }

    public Coupon(String coupon) {
        this.coupon = coupon;
    }

    public Coupon(String coupon, double discount, Status status) {
        this.coupon = coupon;
        this.discount = discount;
        this.status = status;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coupon != null ? coupon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupon)) {
            return false;
        }
        Coupon other = (Coupon) object;
        if ((this.coupon == null && other.coupon != null) || (this.coupon != null && !this.coupon.equals(other.coupon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return coupon;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

}
