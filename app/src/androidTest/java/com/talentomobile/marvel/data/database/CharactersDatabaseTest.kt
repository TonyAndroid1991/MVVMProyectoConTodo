package com.talentomobile.marvel.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.TestUtils
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersDatabaseTest : TestCase() {

    private lateinit var sut: CharactersDatabase
    private lateinit var dao: MarvelCharactersDAO

    /**IMPORTANTE!!!!!!!!!!!
     *Al parecer cuando estas haciendo tests de instrumentacion tienes que decalrar los metodos setup() y tearDown() como publicos
     * si no da error*/

    @Before
    public override fun setUp() {
        /**Esta es la manera correcta de obtener el contexto, pero creo que esto funciona si estas usand @RunWith(AndroidJUnit4::class)*/
        val context = ApplicationProvider.getApplicationContext<Context>()
        /**Esta es la manera correcta de inicializar una base de datos para testear */
        sut = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java).build()
        dao = sut.marvelCharactersDao()
    }

    @After
    public override fun tearDown() {
        sut.close()
    }


    @Test
    fun write_and_read_data() = runBlocking{
        dao.saveCharacters(TestUtils().listOfMarvelCharacter)
        val result = dao.getAllCharacters()
        assertEquals(TestUtils().listOfMarvelCharacter, result)
    }
}