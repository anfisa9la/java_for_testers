package ru.stqa.mantis.model;

public record IssueData(String summary, String description, long project, long category) {

    public IssueData() {
        this("","",0L,1L);
    }

    public IssueData withSummary(String summary){
        return new IssueData(summary,this.description,this.project,this.category);
    }

    public IssueData withDescription(String description){
        return new IssueData(this.summary,description,this.project,this.category);
    }

    public IssueData withProject(long project){
        return new IssueData(this.summary,this.description,project,this.category);
    }

    public IssueData withCategory(long category){
        return new IssueData(this.summary,this.description,this.project,category);
    }
}