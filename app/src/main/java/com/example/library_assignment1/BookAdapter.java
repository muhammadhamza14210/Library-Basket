package com.example.library_assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> bookList) {
        super(context, 0, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        // Get the data item for this position
        Book book = getItem(position);

        // Lookup view for data population
        TextView textViewTitle = convertView.findViewById(R.id.text_view_title);
        TextView textViewAuthor = convertView.findViewById(R.id.text_view_author);
        TextView textViewGenre = convertView.findViewById(R.id.text_view_genre);
        TextView textViewYear = convertView.findViewById(R.id.text_view_year);
        TextView textViewReadStatus = convertView.findViewById(R.id.text_view_read_status);

        // Populate the data into the template view using the data object
        if (book != null) {
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText(book.getAuthor());
            textViewGenre.setText(book.getGenre());
            textViewYear.setText(String.valueOf(book.getPublicationYear()));

            // Set the text of the read status TextView based on the read status of the book
            if (book.isReadStatus()) {
                textViewReadStatus.setText("Read");
            } else {
                textViewReadStatus.setText("Not Read");
            }

            int readStatusColor = book.isReadStatus() ? ContextCompat.getColor(getContext(), R.color.green) :
                    ContextCompat.getColor(getContext(), R.color.red);
            textViewReadStatus.setTextColor(readStatusColor);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
