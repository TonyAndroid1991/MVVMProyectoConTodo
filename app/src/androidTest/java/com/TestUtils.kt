package com

import com.talentomobile.marvel.data.models.*

class TestUtils {

    val itemsList = listOf(
        Item("item1", "resource1"),
        Item("item2", "resource2"),
        Item("item3", "resource3")
    )
    val xxxItemsList = listOf(
        ItemXXX("1", "resource1", "type1"),
        ItemXXX("2", "resource2", "type2"),
        ItemXXX("3", "resource3", "type3")
    )
    val events = Events(1, "", itemsList, 1)
    val series = Series(1, "", itemsList, 1)
    val comics = Comics(1, "", itemsList, 1)
    val stories = Stories(1, "",xxxItemsList,1)
    val thumbnail = Thumbnail("1", "path")
    val marvelCharacter = MarvelCharacter(comics, "1", events, 1, "", "", "", series, stories,thumbnail)
    val listOfMarvelCharacter: List<MarvelCharacter> =
        listOf(marvelCharacter)
    val data = Data(1,1,1,listOfMarvelCharacter,1)
    val allCharactersResponse = AllCharactersResponse("","", 1, "", data,"","")

}