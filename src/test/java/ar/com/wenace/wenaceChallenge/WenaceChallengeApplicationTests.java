package ar.com.wenace.wenaceChallenge;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WenaceChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPrice() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/price")
				.param("time", new Timestamp(Calendar.getInstance().getTimeInMillis() + 30000).toString())
				.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPrices() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/prices")
				.param("time", new Timestamp(Calendar.getInstance().getTimeInMillis() + 20000).toString())
				.param("time2", new Timestamp(Calendar.getInstance().getTimeInMillis() + 30000).toString())
				.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}
}
