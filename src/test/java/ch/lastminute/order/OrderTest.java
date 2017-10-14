package ch.lastminute.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {

	@Test
	public void emptyOrderTest() {
		final Order order = new Order();
		assertEquals(order.size(), 0);
	}

}
