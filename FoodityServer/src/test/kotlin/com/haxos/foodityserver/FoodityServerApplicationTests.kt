package com.haxos.foodityserver

import com.haxos.foodityserver.rest.notes.note.Note
import com.haxos.foodityserver.rest.notes.category.NotesCategoriesRepository
import com.haxos.foodityserver.rest.notes.category.NotesCategory
import com.haxos.foodityserver.rest.notes.note.INotesRepository
import com.haxos.foodityserver.rest.profiles.Profile
import com.haxos.foodityserver.rest.profiles.ProfilesRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FoodityServerApplicationTests(
    @Autowired val profileRepo: ProfilesRepository,
    @Autowired val notesCategoryRepo: NotesCategoriesRepository,
    @Autowired val notesRepo: INotesRepository
) {

	@Test
	fun contextLoads() {
		profileRepo.deleteAll()
		notesCategoryRepo.deleteAll()
		notesRepo.deleteAll()

		val jkowalski = Profile(
			username = "jkowalski",
			firstName = "Janusz",
			lastName = "Kowalski",
		)
		val anowak = Profile(
			username = "anowak",
			firstName = "Anna",
			lastName = "Nowak",
		)
		val jkaczynski = Profile(
			username = "jkaczynski",
			firstName = "Jarosław",
			lastName = "Kaczyński"
		)

		val desserts = NotesCategory(
			name = "Desserts",
			thumbnail = 0,
			profile = jkowalski)

		val tea = NotesCategory(
			name = "Tea",
			thumbnail = 0,
			profile = jkowalski)

		val meat = NotesCategory(
			name = "Meat",
			thumbnail = 0,
			profile = anowak)

		val note1 = Note(
			name = "Panna cotta",
			category = desserts,
			description = "Panna cotta is an Italian dessert of sweetened cream thickened with gelatin and molded. The cream may be aromatized with coffee, vanilla, or other flavorings.",
			thumbnail = 0)

		val note2 = Note(
			name = "Tiramisu",
			category = desserts,
			description = "Tiramisu is a coffee-flavoured Italian dessert. It is made of ladyfingers dipped in coffee, layered with a whipped mixture of eggs, sugar, and mascarpone cheese, flavoured with cocoa.",
			thumbnail = 0)

		val note3 = Note(
			name = "Chocolate cake",
			category = desserts,
			description = "Chocolate cake or chocolate gâteau is a cake flavored with melted chocolate, cocoa powder, or both.",
			thumbnail = 0)

		val note4 = Note(
			name = "Steak",
			category = meat,
			description = "A steak is a meat generally sliced across the muscle fibers, potentially including a bone.",
			thumbnail = 0)

		val note5 = Note(
			name = "Chicken tenders",
			category = meat,
			description = "Chicken fingers, also known as chicken tenders, chicken goujons, chicken strips or chicken fillets, are chicken meat prepared from the pectoralis minor muscles of the animal.",
			thumbnail = 0)

		val note6 = Note(
			name = "Sencha Kagoshima",
			category = tea,
			description = "Japanese green tea.",
			thumbnail = 0)

		val note7 = Note(
			name = "Eastern Beauty",
			category = tea,
			description = "Famous chinese oolong tea.",
			thumbnail = 0)

		profileRepo.saveAll(mutableListOf(jkaczynski, anowak, jkowalski))
		notesCategoryRepo.saveAll(mutableListOf(desserts, meat, tea))
		notesRepo.saveAll(mutableListOf(note1, note2, note3, note4, note5, note6, note7))
	}
}
