package com.ksh.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.ksh.Spring_Data_Mongo.SpringDataMongoApplication;
import com.ksh.documents.User;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = SpringDataMongoApplication.class)
public class UserRepositoryTest {
 
	@Autowired
    private UserRepository userRepository;
	  
	@Test
	public void contexLoads() throws Exception {
		assertThat(userRepository).isNotNull();
	}
	
//    @Test
//    public void saveAndFindTest() {
//    	System.out.println("Testing SaveAndFind Test");
//    	User user = getDummyObject();
//    	User user2 = userRepository.save(user);
//        Optional<User> found = userRepository.findById(user2.getId());
//        assertThat(found.get().getfName()).isEqualTo(user2.getfName());
//    }
//    @Test
//    public void saveAndFindAllTest() {
//    	System.out.println("Testing SaveAndFindAll Test");
//    	User user = getDummyObject();
//    	userRepository.save(user);
//    	List<User> users = userRepository.findAll();
//    	assertThat(users.size()).isEqualTo(1);
//    }
//    @Test
//    public void saveAndCountByEmailAndIsActiveTest() {
//    	System.out.println("Testing saveAndCountByEmailAndIsActiveTest Test");
//    	User user = getDummyObject();
//    	User user2 = userRepository.save(user);
//    	int count = userRepository.countByEmailAndIsActive(user2.getEmail(), user2.isActive());
//    	assertThat(1).isNotEqualTo(count);
//    }
//    @Test
//    public void saveAndFindByBirthDateBetweenTest() {
//    	System.out.println("Testing saveAndFindByBirthDateBetweenTest Test");
//    	User user = getDummyObject();
//    	userRepository.save(user);
//    	List<User> users = userRepository.findByBirthDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
//    	assertThat(1).isNotEqualTo(users.size());
//    }
    
    private User getDummyObject() {
    	User user = new User();
    	user.setActive(true);
    	user.setBirthDate(LocalDate.now());
    	user.setEmail("ks@ks.com");
    	user.setfName("Kshitij");
    	user.setlName("Solanki");
    	user.setPinCode(123456);
    	return user;
    }
    
}
