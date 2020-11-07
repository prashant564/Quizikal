package com.example.quizzio.utils

import com.example.quizzio.views.fragments.AnswerFragment

object AppConstants {

    const val BASE_URL = "https://quizikal.herokuapp.com/api/v1/"
    const val TRIVIA_LIMIT = 15

    var categoryType = "categoryType"
    var categoryTag = "categoryTag"
    var colorId = "colorId"
    var quizData = "quizData"
    var fragmentTag = "fragmentTag"

    object CategoryTag{
        var Entertainment = "Entertainment"
        var Music = "Music"
        var General = "General"
        var SportsAndLeisure = "Sports and Leisure"
        var HistoryAndHolidays = "History and Holidays"
        var FoodAndDrinks = "Food and Drinks"
        var ToysAndGames = "Toys and Games"
        var ScienceAndNature = "Science and Nature"
        var PeopleAndPlaces = "People and Places"
        var Language = "Language"
        var Kids = "Kids"
        var ReligionAndMythology = "Religion and Mythology"
        var ArtAndLiterature = "Art and Literature"
        var TechAndVideoGames = "Tech and Video Games"
        var Mathematics = "Mathematics"
    }

    object FragmentTag{
        var AnswerFragment = "AnswerFragment"
        var FavoritesFragment = "FavoritesFragment"
        var QuestionsFragment= "QuestionsFragment"
    }
}