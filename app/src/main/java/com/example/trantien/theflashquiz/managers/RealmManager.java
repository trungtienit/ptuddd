package com.example.trantien.theflashquiz.managers;

import android.util.Log;

import com.example.trantien.theflashquiz.mvc.models.QuizBank;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Zuka on 10/2/18.
 */
public class RealmManager {

    private static RealmManager instance;
    private final Realm realm;

    public RealmManager() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmManager getInstance() {
        if (instance == null) {
            instance = new RealmManager();
        }
        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects
    public void clearAll() {

//        realm.beginTransaction();
//        realm.clear(QuizBank.class);
//        realm.commitTransaction();
    }


    //find all objects in the Book.class
    public RealmResults<QuizBank> getQuestionBanks() {
        return realm.where(QuizBank.class).findAll();
    }

    //query a single item with the given id
    public QuizBank getQuestionBank(String id) {

        return realm.where(QuizBank.class).equalTo("id", id).findFirst();
    }


//    //query example
//    public RealmResults<Book> queryedBooks() {
//
//        return realm.where(Book.class)
//                .contains("author", "Author 0")
//                .or()
//                .contains("title", "Realm")
//                .findAll();
//
//    }

    public void addQuestionBank(QuizBank quizBank){
        try {
            realm.beginTransaction();
            realm.insertOrUpdate(quizBank);
            realm.commitTransaction();
        }catch (Exception e){
            Log.d("ADD REALM ERROR", e.getMessage());
            realm.beginTransaction();
             RealmResults <QuizBank> quizBank1 = realm.where(QuizBank.class).findAll();
            quizBank1.deleteAllFromRealm();
            realm.insertOrUpdate(quizBank);
            realm.commitTransaction();
        }

    }
}
