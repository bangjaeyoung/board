package com.example.board.api.v1.board.controller

import com.example.board.api.v1.board.controller.dto.RequestDto
import com.example.board.api.v1.board.service.BoardService
import com.example.board.mapper.BoardMapper
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/v1/boards")
class BoardController @Autowired constructor(
    val v1BoardService: BoardService,
    val v1BoardMapper: BoardMapper
) {
    @PostMapping
    fun postBoard(@Valid @RequestBody request: RequestDto.Post): ResponseEntity<Long> {
        return ResponseEntity(v1BoardService.postBoard(request), HttpStatus.CREATED)
    }
    
    @PatchMapping("/{board-id}")
    fun updateBoard(
        @Positive @PathVariable("board-id") boardId: Long,
        @Valid @RequestBody request: RequestDto.Update
    ): ResponseEntity<HttpStatus> {
        if (v1BoardMapper.selectBoardById(boardId) == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        
        v1BoardService.updateBoard(boardId, request)
        return ResponseEntity(HttpStatus.OK)
    }
}
