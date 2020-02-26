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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_Product", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findAllAvailable", query = "SELECT p FROM Product p WHERE p.status.statusName = 'Active' AND p.quantity > 0 ORDER BY p.createDate DESC"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByImageURL", query = "SELECT p FROM Product p WHERE p.imageURL = :imageURL"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByCreateDate", query = "SELECT p FROM Product p WHERE p.createDate = :createDate"),
    @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status"),
    @NamedQuery(name = "Product.findByModifiedDate", query = "SELECT p FROM Product p WHERE p.modifiedDate = :modifiedDate")})
public class Product implements Serializable {
    @JoinColumn(name = "status", referencedColumnName = "statusName", nullable = false)
    @ManyToOne(optional = false)
    private Status status;



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 150)
    private String name;
    @Basic(optional = false)
    @Column(name = "imageURL", nullable = false, length = 255)
    private String imageURL;
    @Basic(optional = false)
    @Lob
    @Column(name = "description", nullable = false, length = 2147483647)
    private String description;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "createDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderItem> orderItemList;
    @JoinColumn(name = "modifiedBy", referencedColumnName = "email")
    @ManyToOne
    private Account modifiedBy;
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Category category;

    @Transient
    private String error;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String imageURL, String description, double price, Date createDate, int quantity, Status status) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.quantity = quantity;
        this.status = status;
    }

    public Product(String name, String imageURL, String description, double price, Date createDate, int quantity, Category category, Status status) {
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getStringPrice() {
        return String.format("%.2f", price);
    }

    public String getSubTotal() {
        return String.format("%.2f", price * quantity);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(createDate);
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String getModifiedDate() {
        if (modifiedDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(modifiedDate);
        }
        return null;

    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlTransient
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product: " + name + " " + quantity + " " + error;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
