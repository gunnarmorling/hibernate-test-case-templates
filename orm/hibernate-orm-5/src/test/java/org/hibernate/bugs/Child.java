/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.bugs;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;

/**
 * @author Gunnar Moring
 */
@Entity
public class Child {

	@Id
	@GeneratedValue
	public Long id;

	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name = "Child_Properties")
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	public Map<String, String> properties = new HashMap<>();
}
