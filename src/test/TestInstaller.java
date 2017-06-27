package test;

import static org.junit.Assert.*;

import org.junit.Test;

import AIMSLab_server_interaction.Installer;

/**
 * Testing class for the Installer (and User, incidentally)
 * 
 * @author Enrico Ferro
 *
 */

public class TestInstaller {

	@Test
	public void test() {
		String name = "Mario";
		String surname = "Bianchi";
		String address = "Via di qui";
		String installerId = "0x";
		String username = "M4r10";
		String password = "none";
		Installer installer = new Installer(name, surname, address, installerId, username, password);
		assertTrue("Constructor fail", 	name.equals(installer.getName()) && 
									  	surname.equals(installer.getSurname()) &&
									  	address.equals(installer.getAddress()) &&
									  	installerId.equals(installer.getInstallerID()) &&
									  	username.equals(installer.getUsername()) &&
									  	password.equals(installer.getPassword()));
	}

}
