package com.example.ming.progress;

public interface SaveEditCallBack
{
    void SaveGoal(GoalItem item);
    void ShowDetail(GoalItem item);
    void DeleteGoal(long id);
}