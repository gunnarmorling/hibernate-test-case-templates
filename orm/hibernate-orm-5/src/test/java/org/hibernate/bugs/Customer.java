/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.bugs;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Gunnar Morling
 */
@Entity
public class Customer {

	@Id
	public long id;

	@NotNull
	@Size(max=50)
	public String name;

	@Digits(integer=3, fraction=2)
	public BigDecimal rating;
}
