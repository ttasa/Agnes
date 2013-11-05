package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SALE")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SALE_DATE")
    private Date date;
    
    @OneToMany(mappedBy = "sale")
    private List<SoldItem> soldItems;
    
    public Sale(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
        this.date = new Date();
    }
    
    public Date getDate() {
    	return date;
    }
    
    public List<SoldItem> getSoldItems() {
    	return soldItems;
    }
    
}
