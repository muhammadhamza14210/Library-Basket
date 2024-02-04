package com.example.library_assignment1;

import android.widget.Toast;

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

    public boolean setTitle(String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            return false; // Title length exceeds the maximum limit
        } else {
            this.title = title;
            return true; // Title set successfully
        }
    }

    public String getAuthor() {
        return author;
    }

    public boolean setAuthor(String author) {
        if (author.length() > MAX_AUTHOR_LENGTH) {
            return false; // Author name length exceeds the maximum limit
        } else {
            this.author = author;
            return true; // Author set successfully
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

    public boolean setPublicationYear(int publicationYear) {
        if (String.valueOf(publicationYear).length() != PUBLICATION_YEAR_DIGITS) {
            // Publication year is invalid
            return false;
        } else {
            this.publicationYear = publicationYear;
            return true;
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