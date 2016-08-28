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
@Table(name = "employee_decrease_part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeDecreasePart.findAll", query = "SELECT e FROM EmployeeDecreasePart e"),
    @NamedQuery(name = "EmployeeDecreasePart.findByDecreaseId", query = "SELECT e FROM EmployeeDecreasePart e WHERE e.decreaseId = :decreaseId"),
    @NamedQuery(name = "EmployeeDecreasePart.findByAmount", query = "SELECT e FROM EmployeeDecreasePart e WHERE e.amount = :amount")})
public class EmployeeDecreasePart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "decrease_id")
    private Integer decreaseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
    @ManyToOne(optional = false)
    private Part partId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public EmployeeDecreasePart() {
    }

    public EmployeeDecreasePart(Integer decreaseId) {
        this.decreaseId = decreaseId;
    }

    public EmployeeDecreasePart(Integer decreaseId, int amount) {
        this.decreaseId = decreaseId;
        this.amount = amount;
    }

    public Integer getDecreaseId() {
        return decreaseId;
    }

    public void setDecreaseId(Integer decreaseId) {
        this.decreaseId = decreaseId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Part getPartId() {
        return partId;
    }

    public void setPartId(Part partId) {
        this.partId = partId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (decreaseId != null ? decreaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeDecreasePart)) {
            return false;
        }
        EmployeeDecreasePart other = (EmployeeDecreasePart) object;
        if ((this.decreaseId == null && other.decreaseId != null) || (this.decreaseId != null && !this.decreaseId.equals(other.decreaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.EmployeeDecreasePart[ decreaseId=" + decreaseId + " ]";
    }
    
}
