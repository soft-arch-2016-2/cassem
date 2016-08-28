/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabianlm17-toshiba
 */
@Entity
@Table(name = "part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Part.findAll", query = "SELECT p FROM Part p"),
    @NamedQuery(name = "Part.findByPartId", query = "SELECT p FROM Part p WHERE p.partId = :partId"),
    @NamedQuery(name = "Part.findByName", query = "SELECT p FROM Part p WHERE p.name = :name"),
    @NamedQuery(name = "Part.findByStock", query = "SELECT p FROM Part p WHERE p.stock = :stock"),
    @NamedQuery(name = "Part.findByMaxStock", query = "SELECT p FROM Part p WHERE p.maxStock = :maxStock"),
    @NamedQuery(name = "Part.findByProvider", query = "SELECT p FROM Part p WHERE p.provider = :provider"),
    @NamedQuery(name = "Part.findByPrice", query = "SELECT p FROM Part p WHERE p.price = :price"),
    @NamedQuery(name = "Part.findByCategory", query = "SELECT p FROM Part p WHERE p.category = :category")})
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "part_id")
    private Integer partId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "max_stock")
    private String maxStock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "provider")
    private String provider;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partId")
    private Collection<CarHasPart> carHasPartCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partId")
    private Collection<EmployeeDecreasePart> employeeDecreasePartCollection;

    public Part() {
    }

    public Part(Integer partId) {
        this.partId = partId;
    }

    public Part(Integer partId, String name, int stock, String maxStock, String provider, double price, String category) {
        this.partId = partId;
        this.name = name;
        this.stock = stock;
        this.maxStock = maxStock;
        this.provider = provider;
        this.price = price;
        this.category = category;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(String maxStock) {
        this.maxStock = maxStock;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<CarHasPart> getCarHasPartCollection() {
        return carHasPartCollection;
    }

    public void setCarHasPartCollection(Collection<CarHasPart> carHasPartCollection) {
        this.carHasPartCollection = carHasPartCollection;
    }

    @XmlTransient
    public Collection<EmployeeDecreasePart> getEmployeeDecreasePartCollection() {
        return employeeDecreasePartCollection;
    }

    public void setEmployeeDecreasePartCollection(Collection<EmployeeDecreasePart> employeeDecreasePartCollection) {
        this.employeeDecreasePartCollection = employeeDecreasePartCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partId != null ? partId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Part)) {
            return false;
        }
        Part other = (Part) object;
        if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Part[ partId=" + partId + " ]";
    }
    
}
