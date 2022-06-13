package com.example.instagram.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.R
import com.example.instagram.data.*
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.main.home.HomeFragment
import com.example.instagram.main.profile.ProfileFragment
import com.example.instagram.main.reels.ReelsFragment
import com.example.instagram.main.search.SearchFragment
import com.example.instagram.main.shop.ShopFragment
import com.example.instagram.room.InstagramDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var instaDB : InstagramDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instaDB = InstagramDatabase.getInstance(this)!!


        insertUserDummyData()
        insertStoryDummyData()
        insertPostDummyData()
        insertCommentDummyData()
        insertReplyDummyData()

        initBottomNavigation()

    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{
            item ->
            when (item.itemId) {

                R.id.homeFragment -> {  // 홈 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {  // 검색 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.reelsFragment -> {  // 검색 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ReelsFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.shopFragment -> {  // 보관함 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ShopFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.profileFragment -> {  // 프로필필 화면 실행
                   supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ProfileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun insertUserDummyData() {

        if(instaDB.userDao().getUsers().size > 1) {
            return
        }

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "ddobby", "password1", R.drawable.profile_ex1, "도비" )
        )

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "ally", "password2", R.drawable.profile_ex2, "앨리")
        )

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "blue", "password3", R.drawable.profile_ex3, "블루")
        )

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "luna", "password4", R.drawable.profile_ex1, "루나" )
        )

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "harry", "password5", R.drawable.profile_ex2, "해리")
        )

        instaDB.userDao().insert(
            User(instaDB.userDao().getUsers().size + 1, "cocoa", "password6", R.drawable.profile_ex3, "코코아")
        )
    }

    private fun insertStoryDummyData() {

        if(instaDB.storyDao().getStories().isNotEmpty()) {
            return
        }

        instaDB.storyDao().insert(
            Story(4, R.drawable.story_dummy1, "")
        )

        instaDB.storyDao().insert(
            Story(6, R.drawable.story_dummy2, "")
        )

        instaDB.storyDao().insert(
            Story(3, R.drawable.story_dummy3, "")
        )

        instaDB.storyDao().insert(
            Story(1, R.drawable.story_dummy4, "")
        )
    }

    private fun insertPostDummyData() {

        if(instaDB.postDao().getPosts().isNotEmpty()) {
            return
        }

        instaDB.postDao().insert(
            Post(2, R.drawable.profile_ex1, "얼음 깨기 너무 재밌었다!", false, "")
        )

        instaDB.postDao().insert(
            Post(5, R.drawable.profile_ex2, "한강 최고ㅎㅎ", false, "")
        )

        instaDB.postDao().insert(
            Post(1, R.drawable.profile_ex3, "차가 마시고 싶은 날...", false, "")
        )
    }

    private fun insertCommentDummyData() {

        if(instaDB.CommentDao().getComments().size > 1) {
            return
        }

        instaDB.CommentDao().insert(
            Comment(1, 1, "댓글 예시", "", 0)
        )

        instaDB.CommentDao().insert(
            Comment(2, 2, "댓글 예시", "", 0)
        )

        instaDB.CommentDao().insert(
            Comment(3, 3, "댓글 예시", "", 0)
        )
    }

    private fun insertReplyDummyData() {
        if(instaDB.CommentDao().getReplies().isNotEmpty()) {
            return
        }

        instaDB.CommentDao().insertReply(
            Reply(5, 3, 3, "헐 대박이다", "", 0)
        )

        instaDB.CommentDao().insertReply(
            Reply(6, 3, 3, "진짜?", "", 0)
        )

        instaDB.CommentDao().insertReply(
            Reply(1, 2, 2, "와~~~", "", 0)
        )

        instaDB.CommentDao().insertReply(
            Reply(5, 1, 1, "에엥", "", 0)
        )

        instaDB.CommentDao().insertReply(
            Reply(5, 1, 1, "박박이다리", "", 0)
        )
    }
}