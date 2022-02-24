package com.example.text_notes_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public String title;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "date")
    public long date;

    public Note()  {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (uid != note.uid) return false;
        if (date != note.date) return false;
        if (title != null ? !title.equals(note.title) : note.title != null) return false;
        return text != null ? text.equals(note.text) : note.text == null;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    protected Note(Parcel in) {
        uid = in.readInt();
        title = in.readString();
        text = in.readString();
        date = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeLong(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
