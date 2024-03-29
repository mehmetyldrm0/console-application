package javaconsoleapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "information_table")
public class Information extends BaseEntity {

	@Column(name = "information_rate")
	private double informationRate;

	// getter ve setter metodları

	public double getInformationRate() {
		return informationRate;
	}

	public void setInformationRate(double informationRate) {
		this.informationRate = informationRate;
	}

}
