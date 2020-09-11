package org.lakshya.onlineexam.model;

public class GetExamDetails
{
    private String examTitle;
    private String examDate;
    private String examImage;
    private String examUrl;

    public GetExamDetails() {
    }

    public GetExamDetails(String examTitle, String examDate, String examImage, String examUrl) {
        this.examTitle = examTitle;
        this.examDate = examDate;
        this.examImage = examImage;
        this.examUrl = examUrl;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamImage() {
        return examImage;
    }

    public void setExamImage(String examImage) {
        this.examImage = examImage;
    }

    public String getExamUrl() {
        return examUrl;
    }

    public void setExamUrl(String examUrl) {
        this.examUrl = examUrl;
    }
}
