package org.hibernate.bugs;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
			.addAnnotatedClass( Parent.class )
			.addAnnotatedClass( Child.class )
			.buildMetadata();

		sf = metadata.buildSessionFactory();
	}

	// Add your tests, using standard JUnit.

	@Test
	public void hhh123Test() throws Exception {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();

		Parent parent1 = new Parent();
		parent1.name = "Parent 1";

		Child child1 = new Child();
		child1.properties.put( "some_setting", "a value" );
		child1.properties.put( "another_setting", "another value" );
		child1.properties.put( "yet_another_setting", "another value" );
		parent1.children.add( child1 );

		session.persist( parent1 );

		transaction.commit();
		session.clear();

		transaction = session.beginTransaction();

		Parent loaded = session.get( Parent.class, parent1.id );
		assertEquals( 1, loaded.children.size() );

		transaction.commit();
		session.close();
	}
}
