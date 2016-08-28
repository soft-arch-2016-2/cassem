/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabianlm17-toshiba
 */
@Entity
@Table(name = "sale_has_car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleHasCar.findAll", query = "SELECT s FROM SaleHasCar s"),
    @NamedQuery(name = "SaleHasCar.findByIndividualSaleId", query = "SELECT s FROM SaleHasCar s WHERE s.individualSaleId = :individualSaleId"),
    @NamedQuery(name = "SaleHasCar.findByAmount", query = "SELECT s FROM SaleHasCar s WHERE s.amount = :amount")})
public class SaleHasCar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "individual_sale_id")
    private Integer individualSaleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    @ManyToOne(optional = false)
    private Car carId;
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id")
    @ManyToOne(optional = false)
    private Sale saleId;

    public SaleHasCar() {
    }

    public SaleHasCar(Integer individualSaleId) {
        this.individualSaleId = individualSaleId;
    }

    public SaleHasCar(Integer individualSaleId, int amount) {
        this.individualSaleId = individualSaleId;
        this.amount = amount;
    }

    public Integer getIndividualSaleId() {
        return individualSaleId;
    }

    public void setIndividualSaleId(Integer individualSaleId) {
        this.individualSaleId = individualSaleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Sale getSaleId() {
        return saleId;
    }

    public void setSaleId(Sale saleId) {
        this.saleId = saleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (individualSaleId != null ? individualSaleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleHasCar)) {
            return false;
        }
        SaleHasCar other = (SaleHasCar) object;
        if ((this.individualSaleId == null && other.individualSaleId != null) || (this.individualSaleId != null && !this.individualSaleId.equals(other.individualSaleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.SaleHasCar[ individualSaleId=" + individualSaleId + " ]";
    }
    
}
