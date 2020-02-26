/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tbl_Privilege", catalog = "ShopDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByPrivilegeName", query = "SELECT p FROM Privilege p WHERE p.privilegeName = :privilegeName")})
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "privilegeName", nullable = false, length = 50)
    private String privilegeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilege")
    private List<Account> accountList;

    public Privilege() {
    }

    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (privilegeName != null ? privilegeName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.privilegeName == null && other.privilegeName != null) || (this.privilegeName != null && !this.privilegeName.equals(other.privilegeName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return privilegeName;
    }
    
}
