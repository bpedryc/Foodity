package com.haxos.foodityserver

import com.haxos.foodityserver.notes.Note
import com.haxos.foodityserver.notes.NotesCategory
import com.haxos.foodityserver.profiles.Profile
import com.haxos.foodityserver.profiles.ProfilesRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FoodityServerApplicationTests(
	@Autowired val profileRepo: ProfilesRepository
) {

	@Test
	fun contextLoads() {
		profileRepo.deleteAll()

		val desserts = NotesCategory(
			name = "Desserts",
			thumbnail = 0,
			notes = arrayListOf(
				Note("Panna cotta", 0, ArrayList()),
				Note("Tiramisu", 0, ArrayList()),
				Note("Chocolate cake", 0, ArrayList())
			)
		)
		val teas = NotesCategory(
			name = "Teas",
			thumbnail = 0,
			notes = arrayListOf(
				Note("Black keemun", 0, ArrayList()),
				Note("Lung ching", 0, ArrayList()),
				Note("Sencha kagoshima", 0, ArrayList())
			)
		)
		val meat = NotesCategory(
			name = "Meat",
			thumbnail = 0,
			notes = arrayListOf(
				Note("Steak", 0, ArrayList()),
				Note("Chicken tenders", 0, ArrayList()),
			)
		)


		val profile1 = Profile(
			username = "jkowalski",
			firstName = "Janusz",
			lastName = "Kowalski",
			notesCategories = arrayListOf(desserts, teas, meat)
		)
		val profile2 = Profile(
			username = "anowak",
			firstName = "Anna",
			lastName = "Nowak",
			notesCategories = arrayListOf(desserts, teas)
		)
		val profile3 = Profile(
			username = "jkaczynski",
			firstName = "Jarosław",
			lastName = "Kaczyński"
		)
		profileRepo.saveAll(mutableListOf(profile1, profile2, profile3))
	}
}
