package com.db.bexlibrary.BexLibrary;

import com.db.bexlibrary.BexLibrary.javamail.MailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BexLibraryApplicationTests {


	@Autowired
	private MailSender mailSender;


	@Test
	public void testEmail(){
		//mailSender.sendMail("bexlibrary18@gmail.com","aliprd1996@gmail.com","Test mail","Heeeeeeeeeeey");
	}

}
