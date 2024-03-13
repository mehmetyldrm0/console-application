package javaconsoleapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "forex_table")
public class Forex extends BaseEntity {

	@Column(name = "forex_buying")
	private double forexBuying;

	@Column(name = "forex_selling")
	private double forexSelling;

	// getter ve setter metodları

	public double getForexBuying() {
		return forexBuying;
	}

	public void setForexBuying(double forexBuying) {
		this.forexBuying = forexBuying;
	}

	public double getForexSelling() {
		return forexSelling;
	}

	public void setForexSelling(double forexSelling) {
		this.forexSelling = forexSelling;
	}

}
