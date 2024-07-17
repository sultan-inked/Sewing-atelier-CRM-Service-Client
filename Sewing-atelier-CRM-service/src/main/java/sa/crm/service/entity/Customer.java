package sa.crm.service.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_customer", schema = "sewing_atelier")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "c_date_of_creation")
	private Date dateOfCreation;
	
	@Column(name = "c_date_of_last_edit")
	private Date dateOfLastEdit;
	
	@Column(name = "c_first_name")
	private String firstName;
	
	@Column(name = "c_last_name")
	private String lastName;
	
	@Column(name = "c_phone_number")
	private String phoneNumber;
	
	@OneToMany
	private List<Order> orders;
}
