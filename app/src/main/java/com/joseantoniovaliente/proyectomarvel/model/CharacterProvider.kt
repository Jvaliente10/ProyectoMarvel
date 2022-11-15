package com.joseantoniovaliente.proyectomarvel.model

object CharacterProvider {
    fun getCharacters(tipo: String= "madrid"): List<Character> {
        Thread.sleep(2000)
        return (1..100).map {
            Character(
                "Nombre $it",
                "https://loremflickr.com/240/320/$tipo?lock=$it"
            )
        }
    }

}
