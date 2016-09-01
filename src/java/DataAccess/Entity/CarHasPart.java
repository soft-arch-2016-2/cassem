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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "car_has_part", catalog = "dbcassem", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarHasPart.findAll", query = "SELECT c FROM CarHasPart c"),
    @NamedQuery(name = "CarHasPart.findByCarHasPartId", query = "SELECT c FROM CarHasPart c WHERE c.carHasPartId = :carHasPartId")})
public class CarHasPart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_has_part_id", nullable = false)
    private Integer carHasPartId;
    @JoinColumn(name = "car_id", referencedColumnName = "car_id", nullable = false)
    @ManyToOne(optional = false)
    private Car carId;
    @JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false)
    @ManyToOne(optional = false)
    private Part partId;

    public CarHasPart() {
    }

    public CarHasPart(Integer carHasPartId) {
        this.carHasPartId = carHasPartId;
    }

    public CarHasPart( Car carId, Part partId ) {
        this.carId = carId;
        this.partId = partId;
    }

    
    public Integer getCarHasPartId() {
        return carHasPartId;
    }

    public void setCarHasPartId(Integer carHasPartId) {
        this.carHasPartId = carHasPartId;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Part getPartId() {
        return partId;
    }

    public void setPartId(Part partId) {
        this.partId = partId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carHasPartId != null ? carHasPartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarHasPart)) {
            return false;
        }
        CarHasPart other = (CarHasPart) object;
        if ((this.carHasPartId == null && other.carHasPartId != null) || (this.carHasPartId != null && !this.carHasPartId.equals(other.carHasPartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.CarHasPart[ carHasPartId=" + carHasPartId + " ]";
    }
    
}
