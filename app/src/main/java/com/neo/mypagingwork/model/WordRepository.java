package com.neo.mypagingwork.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<PagedList<Word>> mAllWords;  // liveData of list of words object

    public WordRepository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getDatabase(application);
        mWordDao = database.wordDao();
        mAllWords = new LivePagedListBuilder<>(mWordDao.getAllWords(), 10).build();
    }

    public LiveData<PagedList<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        WordRoomDatabase.sExecutorService.execute(() -> mWordDao.insert(word));
    }

//    private class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
//        private WordDao mAsyncTaskDao;
//
//        public InsertAsyncTask(WordDao wordDao) {
//            mAsyncTaskDao = wordDao;
//        }
//
//        @Override
//        protected Void doInBackground(Word... words) {
//            mAsyncTaskDao.insert(words[0]);
//            return null;
//        }
//    }
}
