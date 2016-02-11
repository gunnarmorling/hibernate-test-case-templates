package org.hibernate.bugs;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.junit.Before;
import org.junit.Test;
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
			.addAnnotatedClass( VirtualMachine.class )
			.addAnnotatedClass( Configuration.class )
			.buildMetadata();

		sf = metadata.buildSessionFactory();
	}

	// Add your tests, using standard JUnit.

	@Test
	public void hhh123Test() throws Exception {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();

		VirtualMachine vm1 = new VirtualMachine();
		vm1.setName( "My VM" );

		Configuration config1 = new Configuration();
		config1.getProperties().put( "some_setting", "a value" );
		config1.getProperties().put( "another_setting", "another value" );
		vm1.getConfigurations().add( config1 );

		Configuration config2 = new Configuration();
		config2.getProperties().put( "yet_another_setting", "crazy value" );
		vm1.getConfigurations().add( config2 );

		session.persist( vm1 );

		transaction.commit();
		session.clear();

		transaction = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<VirtualMachine> list = session.createQuery(
				"FROM VirtualMachine vm LEFT JOIN FETCH vm.configurations c LEFT JOIN FETCH c.properties WHERE vm.id = :id" )
			.setParameter( "id", vm1.getId() )
			.setResultTransformer( DistinctRootEntityResultTransformer.INSTANCE )
			.list();

		assertEquals( 1, list.size() );

		transaction.commit();
		session.close();
	}
}
