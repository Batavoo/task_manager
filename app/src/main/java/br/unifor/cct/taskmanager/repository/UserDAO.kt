package br.unifor.cct.taskmanager.repository

import androidx.room.*
import br.unifor.cct.taskmanager.entity.User
import br.unifor.cct.taskmanager.entity.UserWithTasks

@Dao
interface UserDAO {

    @Insert fun insert(user:User)
    @Update fun update(user: User)
    @Delete fun delete(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun findById(id:Int):User

    @Query("SELECT * FROM users WHERE email = :email")
    fun findByEmail(email:String):User

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun findTasksByUserId(userId:Int):UserWithTasks

    @Query("SELECT * FROM users")
    fun findAll():List<User>

}