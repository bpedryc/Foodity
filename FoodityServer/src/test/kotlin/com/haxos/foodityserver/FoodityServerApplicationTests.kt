package com.haxos.foodityserver

import com.haxos.foodityserver.profiles.Profile
import com.haxos.foodityserver.profiles.ProfilesRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FoodityServerApplicationTests(@Autowired val repo: ProfilesRepository) {

	@Test
	fun contextLoads() {
		val profile1 = Profile(
				username = "jkowalski",
				firstName = "Janusz",
				lastName = "Kowalski"
		)
		val profile2 = Profile(
				username = "anowak",
				firstName = "Anna",
				lastName = "Nowak"
		)
		val profile3 = Profile(
				username = "jkaczynski",
				firstName = "Jarosław",
				lastName = "Kaczyński"
		)
		repo.saveAll(mutableListOf(profile1, profile2, profile3))
	}

}
