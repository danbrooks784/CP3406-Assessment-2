package com.example.cp3406assessment2.data

class BookRepositoryImpl: BookRepository {
    override fun getBooks(): MutableList<Book> {
        return mutableListOf(
            Book("A Tale of Two Cities", "Charles Dickens", "Historical", 1859, 392, 392, 4),
            Book("The Little Prince", "Antoine de Saint-Exupéry", "Fantasy", 1943, 260),
            Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Fantasy", 1997, 224, 224, 2),
            Book("And Then There Were None", "Agatha Christie", "Mystery", 1939, 272, 272, 5),
            Book("The Hobbit", "J. R. R. Tolkien", "Fantasy", 1937, 310, 128),
            Book("Alice's Adventures in Wonderland", "Lewis Carroll", "Fantasy", 1865, 96),
            Book("The Catcher in the Rye", "J. D. Salinger", "Fiction", 1951, 234, 80),
            Book("Anne of Green Gables", "Lucy Maud Montgomery", "Fiction", 1908, 299, 299, 3),
            Book("Moby Dick", "Herman Melville", "Adventure", 1851, 635, 30),
            Book("Frankenstein", "Mary Shelley", "Science fiction", 1818, 280, 280, 5),
            Book("Dracula", "Bram Stoker", "Horror", 1897, 354, 302),
            Book("The Adventures of Sherlock Holmes", "Arthur Conan Doyle", "Mystery", 1982, 307, 307, 4),
        )
    }
}