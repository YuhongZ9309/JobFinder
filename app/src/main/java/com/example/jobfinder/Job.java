package com.example.jobfinder;

public class Job {

    public String jobName, jobEmail, jobTitle, jobDescription, jobPrice;


    public Job(String jobName, String jobEmail, String jobTitle, String jobPrice, String jobDescription) {
        this.jobName = jobName;
        this.jobEmail = jobEmail;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobPrice = jobPrice;
    }


    public String getJobName() {
        return jobName;
    }

    public String getJobEmail() {
        return jobEmail;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobPrice() {
        return jobPrice;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", jobEmail='" + jobEmail + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobPrice='" + jobPrice + '\'' +
                '}';
    }
}
