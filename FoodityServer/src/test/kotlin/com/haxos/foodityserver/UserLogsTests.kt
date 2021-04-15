package com.haxos.foodityserver

import com.haxos.foodityserver.logs.INoteLogsRepository
import com.haxos.foodityserver.logs.IProfileLogsRepository
import com.haxos.foodityserver.logs.NoteLog
import com.haxos.foodityserver.logs.ProfileLog
import com.haxos.foodityserver.notes.NotesRepository
import com.haxos.foodityserver.profiles.ProfilesRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class UserLogsTests (
    @Autowired val profilesRepository: ProfilesRepository,
    @Autowired val notesRepository: NotesRepository,
    @Autowired val noteLogsRepository: INoteLogsRepository,
    @Autowired val profileLogsRepository: IProfileLogsRepository
){

    @Test
    fun contextLoads() {

        val profile1 = profilesRepository.findAll()[0]
        val profile2 = profilesRepository.findAll()[1]
        val profile1Id = profile1.getId() ?: return
        val notes = notesRepository.findByProfile(profile1Id)

        val noteLog1 = NoteLog(
            type = NoteLog.Type.CREATE,
            profile = profile1,
            target = notes[0],
            timestamp = LocalDateTime.now()
        )
        val noteLog2 = NoteLog(
            type = NoteLog.Type.EDIT,
            profile = profile1,
            target = notes[0],
            timestamp = LocalDateTime.now()
        )

        val noteLog3 = NoteLog(
            type = NoteLog.Type.DELETE,
            profile = profile1,
            target = notes[0],
            timestamp = LocalDateTime.now()
        )
        val noteLog4 = NoteLog(
            type = NoteLog.Type.CREATE,
            profile = profile1,
            target = notes[1],
            timestamp = LocalDateTime.now()
        )

        val profileLog1 = ProfileLog(
            type = ProfileLog.Type.FOLLOWED,
            profile = profile1,
            target = profile2,
            timestamp = LocalDateTime.now()
        )

        noteLogsRepository.saveAll(mutableListOf(noteLog1, noteLog2, noteLog3, noteLog4))
        profileLogsRepository.save(profileLog1)
    }

}