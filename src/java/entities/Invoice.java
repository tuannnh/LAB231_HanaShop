/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_Invoice", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findByPaypalId", query = "SELECT i FROM Invoice i WHERE i.paypalId = :paypalId"),
    @NamedQuery(name = "Invoice.findByCreateDate", query = "SELECT i FROM Invoice i WHERE i.createDate = :createDate"),
    @NamedQuery(name = "Invoice.findByTotal", query = "SELECT i FROM Invoice i WHERE i.total = :total")})
public class Invoice implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating", precision = 53)
    private Double rating;

    @JoinColumn(name = "coupon", referencedColumnName = "coupon")
    @ManyToOne
    private Coupon coupon;
    @Column(name = "paypalId", length = 255)
    private String paypalId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "createDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<OrderItem> orderItemList;
    @JoinColumn(name = "customer", referencedColumnName = "email", nullable = false)
    @ManyToOne(optional = false)
    private Account customer;

    public void addOrderItem(OrderItem newOrderItem) {
        this.orderItemList.add(newOrderItem);
    }

    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Invoice(Integer id, String paypalId, Date createDate, double total) {
        this.id = id;
        this.paypalId = paypalId;
        this.createDate = createDate;
        this.total = total;
    }

    public Invoice(Date createDate, double total, Account customer) {
        this.createDate = createDate;
        this.total = total;
        this.customer = customer;
    }

    public Invoice(Date createDate, double total, Account customer, Coupon coupon) {
        this.createDate = createDate;
        this.total = total;
        this.customer = customer;
        this.coupon = coupon;
    }

    public Invoice(Date createDate, double total, Account customer, String paypalId) {
        this.createDate = createDate;
        this.total = total;
        this.customer = customer;
        this.paypalId = paypalId;
    }

    public Invoice(Date createDate, double total, Account customer, String paypalId, Coupon coupon) {
        this.createDate = createDate;
        this.total = total;
        this.customer = customer;
        this.paypalId = paypalId;
        this.coupon = coupon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public void setPaypalId(String paypalId) {
        this.paypalId = paypalId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getPurchaseDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlTransient
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Invoice[ id=" + id + " ]";
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
