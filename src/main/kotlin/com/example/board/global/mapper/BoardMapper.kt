package com.example.board.global.mapper

import com.example.board.api.v1.board.domain.Board
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoardMapper {
    fun insertBoard(board: Board)
    fun updateBoard(board: Board)
    fun selectBoardById(boardId: Long): Board?
    fun selectAllBoards(): List<Board>
    fun deleteBoardById(boardId: Long)
    fun deleteAllBoards()
}