package demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ModelApplication;
import com.elearning.model.LessonStep;
import com.elearning.persistence.jparepositories.LessonStepRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelApplication.class)
public class ModelApplicationTests {
	
	@Autowired
	private LessonStepRepository lessonStepRepository;

	@Test
	public void contextLoads() {
		
		List<LessonStep> lessonSteps = lessonStepRepository.findAll();
		
		for(LessonStep ls : lessonSteps ){
			System.out.println( ls );
		}
		
	}

}
