package javaconsoleapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cross_table")
public class Cross extends BaseEntity {

	@Column(name = "cross_rate")
	private double crossRate;
	// getter ve setter metodları

	public double getCrossRate() {
		return crossRate;
	}

	public void setCrossRate(double crossRate) {
		this.crossRate = crossRate;
	}

}
