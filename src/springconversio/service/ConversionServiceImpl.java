package springconversio.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import springconversio.domain.InputNumeral;
import springconversio.domain.Numeral;
import springconversio.domain.Pair;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConversionServiceImpl implements ConversionService {

	private static SessionFactory factory;

	protected final Log logger = LogFactory.getLog(getClass());

	private Romanizer romanizer = new Romanizer();

	public ConversionServiceImpl() {
		if (false)
	    try {
	        Configuration cfg = new Configuration().configure("springconversio//service//hibernate.cfg.xml");
	        factory = cfg.buildSessionFactory();
	    } catch (Throwable ex) {
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
	    }
	}
	
	@Override
	public void convert(InputNumeral numeral) {
	    try {
	    	String decimal = numeral.getNumeral();
	    	String roman = this.romanizer.convertDecimalToRoman(decimal);
	        logger.info("Converted successfully " + decimal + " to " +  roman);
	    	try {
            	Integer decimalInt = Integer.valueOf(decimal);
            	if (false) {
              	Long numID1 = addNumeral(decimalInt, roman);
            	}
            } catch (NumberFormatException e) {
            	logger.info("This cannot happen");
            }
	    } catch (IllegalArgumentException e) {
	        logger.info("Failed to convert numeral");
	    }
	}

    /**
    * Fetches data rows from the database
    */
	@Override
	public List<Pair<String,String>> fetchNumerals() {
        List<Pair<String,String>> pairs = new Vector<Pair<String,String>>();
		if (false) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           	List<Numeral> numerals = session.createQuery("FROM Numeral").list();
            for (Iterator<Numeral> iterator = numerals.iterator(); iterator.hasNext(); ){
                Numeral numeral = iterator.next();
            	Pair<String,String> pair =
            			new Pair<String,String>(
            					Integer.toString(numeral.getDecimalNumeral()),
            					numeral.getRomanNumeral());
            	pairs.add(pair);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null)
            	tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
		} else {
			for (int i = 0; i < 3; ++i) {
				String decimal = "1234";
				String roman = this.romanizer.convertDecimalToRoman(decimal);
				Pair<String,String> pair = new Pair<String,String>(decimal, roman);
				pairs.add(pair);
			}
		}
        return pairs;
	}

    /**
    * Appends data row in the database
    * 
    * @param  decimal  decimal numeral as integer value 
    * @param  roman    roman numeral as string value
    * @return  id of data row
    */
    public Long addNumeral(Integer decimal, String roman) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long numeralID = null;
        try {
            tx = session.beginTransaction();
            Numeral numeral = new Numeral(decimal, roman, new Date());
            numeralID = (Long) session.save(numeral);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null)
            	tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return numeralID;
    }
}
