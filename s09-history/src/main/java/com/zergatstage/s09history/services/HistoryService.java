package com.zergatstage.s09history.services;

import com.zergatstage.s09history.model.ExamQuestion;

import java.util.List;

/**
 * @author father
 */
public interface HistoryService {
    List<ExamQuestion> getRandom(int random);
    void fillDB();

    void setVersion(int version);
    int getVersion();

}
