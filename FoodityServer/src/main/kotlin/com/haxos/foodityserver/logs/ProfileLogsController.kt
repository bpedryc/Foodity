package com.haxos.foodityserver.logs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profilelogs")
class ProfileLogsController (
    val noteLogsService: ProfileLogsService
) {
    @GetMapping(params = ["profileId"])
    fun findByProfileId(@RequestParam profileId: Long) : List<ProfileLog> =
        noteLogsService.getFriendLogsOf(profileId)
}