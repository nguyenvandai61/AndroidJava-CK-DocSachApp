package com.example.readbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

import com.example.readbook.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "BOOK_DB";


    // Tên bảng: Note.
    private static final String TABLE_BOOK = "book";

    private static final String COLUMN_BOOK_ID ="Book_Id";
    private static final String COLUMN_BOOK_NAME ="Book_Name";
    private static final String COLUMN_BOOK_AUTHOR ="Book_Author";
    private static final String COLUMN_BOOK_PATH = "Book_Path";
    private static final String COLUMN_BOOK_IMG = "Book_Image";


    public  MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script to create table.

        String script = "CREATE TABLE " + TABLE_BOOK + "("
                + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY," + COLUMN_BOOK_NAME + " TEXT," +
                COLUMN_BOOK_AUTHOR + " TEXT," +  COLUMN_BOOK_IMG + " TEXT"+")";
        // Execute script.
        db.execSQL(script);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);


        // Và tạo lại.
        onCreate(db);
    }

    public void createDefaultNotesIfNeed()  {
        int count = this.getBooksCount();
        if(count ==0 ) {
            Book book1 = new Book(1,
                    "SherLock Holmes","Connan Doyle");
            Book book2 = new Book(2,
                    "Draemon","ABC");
            this.addBook(book1);
            this.addBook(book2);
        }
    }


    public void addBook(Book book) {
        Log.i(TAG, "MyDatabaseHelper.addBook ... " + book.getmName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, book.getmName());
        values.put(COLUMN_BOOK_AUTHOR, book.getmAuthor());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_BOOK, null, values);


        // Đóng kết nối database.
        db.close();
    }


    public Book getBook(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOK, new String[] { COLUMN_BOOK_ID,
                        COLUMN_BOOK_NAME, COLUMN_BOOK_AUTHOR }, COLUMN_BOOK_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return note
        return book;
    }


    public List<Book> getAllBooks() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<Book> bookList = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setmId(Integer.parseInt(cursor.getString(0)));
                book.setmName(cursor.getString(1));
                book.setmAuthor(cursor.getString(2));

                // Thêm vào danh sách.
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        // return note list
        return bookList;
    }

    public int getBooksCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateBook(Book book) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + book.getmName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, book.getmName());
        values.put(COLUMN_BOOK_AUTHOR, book.getmAuthor());
        // updating row
        return db.update(TABLE_BOOK, values, COLUMN_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getmId())});
    }

    public void deleteBook(Book book) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + book.getmName() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, COLUMN_BOOK_ID + " = ?",
                new String[] { String.valueOf(book.getmId()) });
        db.close();
    }
}


