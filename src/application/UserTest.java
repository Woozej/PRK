package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserTest {

User user;
	
	@BeforeEach
	public void createUser() {
		user = new User(1,"kkolcan","sad",3);
	}

	@Test
	void testGetIdUser() {
		assertEquals(1, user.getIdUser());
	}

	@Test
	void testGetUserName() {
		assertEquals("kkolcan", user.getUserName());
	}

	@Test
	void testGetUserPassword() {
		assertEquals("sad", user.getUserPassword());
	}

	@Test
	void testGetAccessLevel() {
		assertEquals(3, user.getAccessLevel());
	}


	@Test
	void testGetVAccessLevel() {
		assertEquals("Administrator", user.getVAccessLevel());
	}

	@Test
	void testToString() {
		assertEquals("kkolcan", user.getUserName());
	}

}
