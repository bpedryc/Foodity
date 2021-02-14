package com.haxos.foodityserver

import com.haxos.foodityserver.users.User
import com.haxos.foodityserver.users.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FoodityServerApplicationTests(@Autowired val repo: UserRepository) {

	@Test
	fun contextLoads() {
		val u1 = User("jkowalski", "j.kowalski123@gmail.com", "haslo123")
		val u2 = User("anowak", "a.nowak@gmail.com", "haslo321")
		repo.save(u1)
		repo.save(u2)
	}

}
