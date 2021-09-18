package com.ajijetpackpro.finalsub.dummydata

import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.data.entity.MovieEntity
import com.ajijetpackpro.finalsub.data.entity.TvshowEntity
import com.ajijetpackpro.finalsub.data.response.*
import java.util.ArrayList

object MovieDummy {

    fun getDummyMovie(): List<MovieEntity> {
        val moviesItem = ArrayList<MovieEntity>()

        moviesItem.add(
            MovieEntity(
                337404,
                "Cruella",
                "2021-05-26",
                8.6,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg"
            )
        )
        moviesItem.add(
            MovieEntity(
                423108,
                "The Conjuring: The Devil Made Me Do It",
                "2021-05-25",
                8.2,
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg"
            )
        )
        return moviesItem
    }

    fun getDummyDetailMovie(): List<MovieEntity> {
        val movieDetail = ArrayList<MovieEntity>()

        movieDetail.add(
            MovieEntity(
                337404,
                "Cruella",
                "2021-05-26",
                8.6,
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg"
            )
        )
        return movieDetail
    }

    fun getDummyTvshow(): List<TvshowEntity> {
        val tvshowsItem = ArrayList<TvshowEntity>()

        tvshowsItem.add(
            TvshowEntity(
                84958,
                "Loki",
                "2021-06-09",
                8.1,
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg"

            )
        )
        tvshowsItem.add(
            TvshowEntity(
                76669,
                "Elite",
                "2018-10-05",
                8.2,
                "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
                "/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
                "/1qOA3kMtQO8bjnW8M2smjA8tp10.jpg",

                )
        )
        return tvshowsItem
    }

    fun getDummyDetailTvshow(): List<TvshowEntity> {
        val tvshowDetail = ArrayList<TvshowEntity>()

        tvshowDetail.add(
            TvshowEntity(
                84958,
                "Loki",
                "2021-06-09",
                8.1,
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg"
            )
        )
        return tvshowDetail
    }
}
