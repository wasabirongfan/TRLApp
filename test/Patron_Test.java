import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class Patron_Test {

	Patron p = new Patron("P2", "Fan47");
	Copy c = new Copy("C3", "Fun with Classes");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	Calendar today = new GregorianCalendar();
	
	@Test
	public void test_verifyPatron()
	{
		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P22", "Rong");

		assertTrue("P1 exists", Patron.verifyPatron(p1.getPatronID()));
		assertFalse("P2 does not exists", Patron.verifyPatron(p2.getPatronID()));

	}
	
	
	@Test
	public void test_holds()
	{
		Patron p1 = new Patron("P1", "Eric");
		Patron p2 = new Patron("P22", "Rong");
		
		p1.setHasHolds(true); 
		StdOut.print("%%%%" + p1.getHasHolds());
		assertTrue("p1 has holds", p1.getHasHolds());
		assertFalse("p2 has holds", p2.getHasHolds());
		
		p1.setHasHolds(false);
		assertFalse("p1 does not has holds", p1.getHasHolds());

	}
	
	
	
	@Test
	public void test_does_not_hasHold()
	{
		Calendar today = new GregorianCalendar();
		Copy c = new Copy("C3", "Fun with Classes");
		Patron p = new Patron("P1", "Fan47");
		c.checkCopyOut(c, p);
		assertFalse("P1 does not have holds", p.processHolds());
	}

	@Test
	public void test_hasHold()
	{
		Copy c = new Copy("C3", "Fun with Classes");
		Patron p = new Patron("P1", "Eric");

		Copy.checkCopyOut(c, p);
		Calendar date = new GregorianCalendar();
		date.set(1500, 1, 1);

		c.setdueDate(date);
		StdOut.println(date.getTime());
		StdOut.println(c);
		c.setOutTo(p);
		StdOut.print(FakeDB.getPatronStore().get(p.getPatronID()));
		StdOut.println(p);// "...." + p.getPatronID() + "..." +
							// FakeDB.getCopyStore().get("P1"));

		// date.add(date.YEAR, -10);
		// Copy c_test = p.getCopiesOut().get(0);
		// p.getCopiesOut().get(0).setdueDate(date);

		assertTrue("the copy is overdue", FakeDB.getPatronStore().get(p.getPatronID()).processHolds());

	}

}