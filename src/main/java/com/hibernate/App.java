package com.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
        Fruits apple=new Fruits();
		Fruits banana=new Fruits(); 
		Fruits orange=new Fruits(); 
		Fruits grapes=new Fruits();
		Fruits kiwi=new Fruits(); 
		Fruits straberry=new Fruits();
		 
        apple.setFruitName("apple");
        apple.setColor("red");
        apple.setTaste("sweet");
        apple.setCost(200.00);
        
        Configuration config=new Configuration().configure().addAnnotatedClass(Fruits.class);
        SessionFactory sf=config.buildSessionFactory();
        
        Session session1=sf.openSession();
        Transaction tx=session1.beginTransaction();
        //session1.persist(apple);
		
        String getData="from Fruits f where f.id= :id ";
   
        Query<Fruits> query=session1.createQuery(getData);
        query.setParameter("id", 1L);
       
        List<Fruits> list=query.list();
        for(Fruits f:list) {
        	System.out.println(f);
        }
        
        tx.commit();
        session1.close();
        
        Session session2 = sf.openSession();
        Transaction tx2 = session2.beginTransaction();
        Query query2 = session2.createSQLQuery("SELECT * FROM FRUITS where id= :id").addEntity(Fruits.class);
        //query2.setMaxResults(3);
        query2.setParameter("id", 2L);
        List<Fruits> l=query2.list();
        for(Fruits fr:l) {
        	System.out.println(fr);
        }
        tx2.commit();
        session2.close();

        
        
        Session session3=sf.openSession();
        Transaction tx3=session3.beginTransaction();
        Query query3=session3.createQuery("delete from Fruits f where f.id= :id");
        query3.setParameter("id", 6L);
        
        query3.executeUpdate();
        tx3.commit();
        session3.close();
        
		/*
		 * Session session4 = sf.openSession(); Transaction tx4 =
		 * session4.beginTransaction();
		 * 
		 * Query<Fruits> query4 = session4.
		 * createSQLQuery("Insert into Fruits (id,fruitName, color, taste, cost)" +
		 * " VALUES (:id,:fname, :c, :t, :co)").addEntity(Fruits.class);
		 * query4.setParameter("id", 7L); query4.setParameter("fname", "blackberry");
		 * query4.setParameter("c", "black"); query4.setParameter("t", "sweet");
		 * query4.setParameter("co", 260.36);
		 * 
		 * query4.executeUpdate(); tx4.commit(); session4.close();
		 */
        
        Session session5 = sf.openSession();
        Transaction tx5 = session5.beginTransaction();

        Query<Fruits> query5 = session5.createQuery("UPDATE Fruits f set f.cost= :cost where f.id= :id");
        query5.setParameter("id", 7L);
        query5.setParameter("cost", 150.20);

        query5.executeUpdate();
        tx5.commit();
        session5.close();

        
    }
}
