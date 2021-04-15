package com.haxos.foodityserver.logs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/notelogs")
class NoteLogsController (
    val noteLogsService: NoteLogsService
) {
    @GetMapping(params = ["profileId"])
    fun findByProfileId(@RequestParam profileId: Long) : List<NoteLog> =
        noteLogsService.getFriendLogOf(profileId)
}