package org.hibernate.bugs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * This template demonstrates how to develop a standalone test case for Hibernate ORM.  Although this is perfectly
 * acceptable as a reproducer, usage of ORMUnitTestCase is preferred!
 */
public class ORMStandaloneTestCase {

	private SessionFactory sf;

	@Before
	public void setup() {
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
			// Add in any settings that are specific to your test. See resources/hibernate.properties for the defaults.
			.applySetting( "hibernate.show_sql", "true" )
			.applySetting( "hibernate.format_sql", "true" )
			.applySetting( "hibernate.hbm2ddl.auto", "create-drop" );

		Metadata metadata = new MetadataSources( srb.build() )
		// Add your entities here.
			.addAnnotatedClass( User.class )
			.addAnnotatedClass( Address.class )
			.addAnnotatedClass( AddressType.class )
			.buildMetadata();

		sf = metadata.buildSessionFactory();
	}

	// Add your tests, using standard JUnit.

	@Test
	public void hhh10125Test() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		AddressType home = new AddressType( "home" );
		session.save( home );

		AddressType work = new AddressType( "work" );
		session.save( work );

		User bob = new User();
		bob.getAddresses().put( home, new Address( "Main Street" ) );
		bob.getAddresses().put( work, new Address( "Business Street" ) );
		session.save( bob );

		List<?> results = session.createQuery( "SELECT KEY(r) FROM User f JOIN f.addresses r" ).list();

		// Fails, it's Address instead of AddressType
		assertEquals( AddressType.class, results.iterator().next().getClass() );

		session.getTransaction().commit();
		session.close();
	}
}
