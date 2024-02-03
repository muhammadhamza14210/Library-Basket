package com.example.library_assignment1;

public class Book {
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_AUTHOR_LENGTH = 30;
    private static final int PUBLICATION_YEAR_DIGITS = 4;

    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean readStatus;

    public Book(String title, String author, String genre, int publicationYear, boolean readStatus) {
        setTitle(title);
        setAuthor(author);
        this.genre = genre;
        setPublicationYear(publicationYear);
        this.readStatus = readStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            this.title = title.substring(0, MAX_TITLE_LENGTH);
        } else {
            this.title = title;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author.length() > MAX_AUTHOR_LENGTH) {
            this.author = author.substring(0, MAX_AUTHOR_LENGTH);
        } else {
            this.author = author;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        // Ensure the publication year is a 4-digit number
        if (String.valueOf(publicationYear).length() == PUBLICATION_YEAR_DIGITS) {
            this.publicationYear = publicationYear;
        } else {
            this.publicationYear = 0;
        }
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String toString() {
        if (readStatus) {
            return title + "\n" + author + "\n" + genre + "\n" + publicationYear + "\nRead";
        } else {
            return title + "\n" + author + "\n" + genre + "\n" + publicationYear + "\nUnread";
        }
    }
}