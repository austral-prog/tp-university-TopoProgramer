package com.university.Utils.TP2.CSVActions;

import com.university.Data.result;

import java.util.List;

public interface csvWrite {
  void writeResultsToCSV(List<result> results, String outputFilePath);
}
