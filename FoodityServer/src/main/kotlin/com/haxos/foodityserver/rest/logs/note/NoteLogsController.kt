package com.haxos.foodityserver.rest.logs.note

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notelogs")
class NoteLogsController (
    val noteLogsService: NoteLogsService
) {
    @GetMapping(params = ["profileId"])
    fun findByProfileId(@RequestParam profileId: Long) : List<NoteLog> =
        noteLogsService.getFriendLogOf(profileId)
}