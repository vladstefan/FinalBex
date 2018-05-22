package com.db.bexlibrary.BexLibrary.controllers;
import static org.junit.Assert.assertEquals;
import com.db.bexlibrary.BexLibrary.entities.User;
import com.db.bexlibrary.BexLibrary.repositories.AuthorRepo;
import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthorControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;
  @MockBean(name = "authorRepo")
  private AuthorRepo authorRepo;
  @MockBean(name = "userRepo")
  private UserRepo userRepo;
  private HttpEntity<Void> header;

  @Before
  public void setUp() throws Exception {
    User user = new User();
    user.setEmail("test@test.com");
    user.setPassword("test1");

    BDDMockito.when(userRepo.findUserByEmail("test@test.com")).thenReturn(user);

    String body = "{\"name\":\"test@test.com\", \"password\":\"test1\"}";
    final HttpHeaders headers = restTemplate
        .postForEntity("/login", body, String.class).getHeaders();

    this.header = new HttpEntity<>(headers);
  }

  @Test
  public void generate() throws Exception {
    BDDMockito.when(authorRepo.findAll()).thenReturn(Collections.emptyList());

    final ResponseEntity<List> exchange = restTemplate
        .exchange("/authors", HttpMethod.GET, header, List.class);

    assertEquals(HttpStatus.OK, exchange.getStatusCode());
  }


}
