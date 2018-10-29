package com.rayworks.album

class AlbumDelegate {

    fun getAlbums(): List<Album> {
        return arrayListOf(
            Album("OK Computer", "Radiohead"),
            Album("The Joshua Tree", "U2"),
            Album("Nevermind", "Nirvana"),
            Album("Thriller", "Michael Jackson"),
            Album("Dark Side of the Moon", "Pink Floyd"),
            Album("Definitely Maybe", "Oasis"),
            Album("Sgt. Pepper's Lonely Hearts Club Band", "The Beatles"),
            Album("Like a Prayer", "Madonna"),
            Album("Appetite For Destruction", "Guns N' Roses"),
            Album("Revolver", "The Beatles"),
            Album(
                "Piano Quintet In A Major Opus 81",
                "Andreas Haefliger and Takacs Quartet",
                composer = "Antonin Dvorak",
                isClassical = true
            ),
            Album(
                "Lieutenant Kije (suite) Opus 60",
                "Lille National Orchestra",
                composer = "Sergei Prokofiev",
                isClassical = true
            ),
            Album(
                "Alessandro Severo - Overture",
                "Academy Of Ancient Music",
                composer = "George Frideric Handel",
                isClassical = true
            ),
            Album(
                "Sinfonia In C Minor",
                "Czech Chamber Philharmonic Orchestra",
                composer = "Josef Barta",
                isClassical = true
            ),
            Album(
                "Sleeping Beauty - Waltz",
                "Kirov Orchestra",
                composer = "Peter Ilich Tchaikovsky",
                isClassical = true
            )

        )
    }
}