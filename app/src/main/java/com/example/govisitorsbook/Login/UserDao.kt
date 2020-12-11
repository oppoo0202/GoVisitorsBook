package com.example.govisitorsbook.Login

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(vararg user : User)

    @Query("SELECT * FROM User")
    fun getUser(): LiveData<List<User>>

    @Query("UPDATE User set autoLogin = :auto WHERE phoneNumber = :phoneNumber")
    fun updateAuto(phoneNumber: String, auto: Boolean = true)


}