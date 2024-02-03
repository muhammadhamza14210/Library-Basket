package com.example.library_assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButtonAddBook;
    private Button mButtonDeleteBook;
    private Button mButtonEditBook;
    private LinearLayout mLayoutNewBook;

    private ArrayList<Book> mBookList;
    private ArrayAdapter<Book> mBookAdapter;

    private int selectedItemPosition = -1; // Variable to store selected item position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associate views with the layout file
        mListView = findViewById(R.id.list_view_books);
        mButtonAddBook = findViewById(R.id.button_add_book);
        mButtonDeleteBook = findViewById(R.id.button_delete_book);
        mButtonEditBook = findViewById(R.id.button_edit_book);
        mLayoutNewBook = findViewById(R.id.layout_book_details);

        mBookList = new ArrayList<>();
        mBookAdapter = new BookAdapter(this, mBookList);

        // Set up ListView
        mListView.setAdapter(mBookAdapter);

        // Add more books as needed
        updateBookCounts();

        mButtonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEditBookDialog(null);
            }
        });

        mButtonEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a book is selected for editing
                if (selectedItemPosition != -1) {
                    Book selectedBook = mBookList.get(selectedItemPosition);
                    showAddEditBookDialog(selectedBook);
                } else {
                    Toast.makeText(MainActivity.this, "Please select a book to edit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mListView.setOnItemClickListener((parent, view, position, id) -> {
            // Store selected item position
            selectedItemPosition = position;
        });

        mButtonDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the selected book from the list
                if (selectedItemPosition != -1) {
                    deleteSelectedItem(selectedItemPosition);
                    mBookAdapter.notifyDataSetChanged();
                    // Clear the selection after deletion
                    selectedItemPosition = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Please select a book to delete", Toast.LENGTH_SHORT).show();
                }
                updateBookCounts();
            }
        });
    }

    private void showAddEditBookDialog(final Book book) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_edit_book, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextTitle = dialogView.findViewById(R.id.dialog_edit_text_title);
        final EditText editTextAuthor = dialogView.findViewById(R.id.dialog_edit_text_author);
        final EditText editTextGenre = dialogView.findViewById(R.id.dialog_edit_text_genre);
        final EditText editTextPublicationYear = dialogView.findViewById(R.id.dialog_edit_text_publication_year);
        final CheckBox checkBoxReadStatus = dialogView.findViewById(R.id.dialog_checkbox_read_status);
        final Button buttonConfirm = dialogView.findViewById(R.id.dialog_button_confirm);
        final Button buttonCancel = dialogView.findViewById(R.id.dialog_button_cancel);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        if (book != null) {
            editTextTitle.setText(book.getTitle());
            editTextAuthor.setText(book.getAuthor());
            editTextGenre.setText(book.getGenre());
            editTextPublicationYear.setText(String.valueOf(book.getPublicationYear()));
            checkBoxReadStatus.setChecked(book.isReadStatus());
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();
                String genre = editTextGenre.getText().toString();
                int publicationYear;
                try {
                    publicationYear = Integer.parseInt(editTextPublicationYear.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid publication year", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean readStatus = checkBoxReadStatus.isChecked();

                if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                    if (book == null) {
                        // Adding a new book
                        addDataToList(new Book(title, author, genre, publicationYear, readStatus));
                    } else {
                        // Editing an existing book
                        editBook(selectedItemPosition, title, author, genre, publicationYear, readStatus);
                    }

                    mBookAdapter.notifyDataSetChanged();
                    updateBookCounts();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }



    private void addDataToList(Book book) {
        mBookList.add(book);
    }

    private void deleteSelectedItem(int index) {
        mBookList.remove(index);
    }

    private void editBook(int index, String title, String author, String genre, int publicationYear, boolean readStatus) {
        Book editedBook = mBookList.get(index);
        editedBook.setTitle(title);
        editedBook.setAuthor(author);
        editedBook.setGenre(genre);
        editedBook.setPublicationYear(publicationYear);
        editedBook.setReadStatus(readStatus);
    }

    private void updateBookCounts() {
        int totalBooks = mBookList.size();
        int readBooks = 0;
        for (Book book : mBookList) {
            if (book.isReadStatus()) {
                readBooks++;
            }
        }

        // Display the counts or use them as needed
        // For example, displaying in a TextView
        TextView totalBooksTextView = findViewById(R.id.total_books_text_view);
        totalBooksTextView.setText("Books: " + totalBooks);

        TextView readBooksTextView = findViewById(R.id.read_books_text_view);
        readBooksTextView.setText("Read: " + readBooks);
    }
}