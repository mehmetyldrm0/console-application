package javaconsoleapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "banknote_table")
public class Banknote extends BaseEntity {

	@Column(name = "banknote_buying")
	private double banknoteBuying;

	@Column(name = "banknote_selling")
	private double banknoteSelling;

	// getter ve setter metodlar

	public double getBanknoteBuying() {
		return banknoteBuying;
	}

	public void setBanknoteBuying(double banknoteBuying) {
		this.banknoteBuying = banknoteBuying;
	}

	public double getBanknoteSelling() {
		return banknoteSelling;
	}

	public void setBanknoteSelling(double banknoteSelling) {
		this.banknoteSelling = banknoteSelling;
	}

}
